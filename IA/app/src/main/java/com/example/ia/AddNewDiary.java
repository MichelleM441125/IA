package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ia.Fragments.Main.MainWorkFragment;
import com.example.ia.Fragments.Others.OtherFragment;
import com.example.ia.Fragments.Personal.PersonalFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddNewDiary extends AppCompatActivity {
    TextView todayText;
    EditText diaryText;
    String toEventName;
    String today;

    private FirebaseFirestore firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_diary);

        // get the event sent from event profile
        Bundle mg = getIntent().getExtras();
        toEventName = mg.getString("info");

        todayText = findViewById(R.id.todayDate);

        // get today’s date and set it as the title of the diary
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        today = format.format(c.getTime());
        todayText.setText(today);

        // set up the image button
        ImageButton b = (ImageButton) this.findViewById(R.id.imageButton2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the textbox is not empty
                if (!(TextUtils.isEmpty(diaryText.getText().toString()))) {
                    // get the text from the textbox and covert it to a diary object
                    String diaryInput = diaryText.getText().toString();
                    Diary newDiary = new Diary(today, diaryInput);

                    //Main
                    for (Events e : MainWorkFragment.allMainEvents) {
                        // go through all the main category events and their title
                        String n = e.getTitle();

                        // if the event is in the main category list
                        if (n.equals(toEventName)) {
                            // create a new diary ArrayList and add the new generated diary into the list
                            ArrayList<Diary> diaryArrayList = e.getDiaries();
                            diaryArrayList.add(newDiary);

                            // replace the original diary list with the new one for the event in fire store
                            firebase.collection("Main").whereEqualTo("title", toEventName)
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                            // update the information with the ID of the document
                                            String ID = ds.getId();
                                            firebase.collection("Main").document(ID)
                                                    .update("diaries", diaryArrayList);
                                            Toast.makeText(getApplicationContext(), "Successfully Added",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "sorry,please try again later",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            finish();
                        }
                    }


                    //Personal
                    for (Events p : PersonalFragment.allPersonalEvents) {
                        String pp = p.getTitle();

                        if (pp.equals(toEventName)) {
                            ArrayList<Diary> pdiaryArrayList = p.getDiaries();
                            pdiaryArrayList.add(newDiary);

                            firebase.collection("Personal").whereEqualTo("title", toEventName)
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                        // update the information with the ID of the document
                                        String ID = ds.getId();
                                        firebase.collection("Personal").document(ID)
                                                .update("diaries", pdiaryArrayList);

                                    }
                                }
                            });
                            finish();
                        }
                    }

                    //Other
                    for (Events o : OtherFragment.allOtherEvents) {
                        String oo = o.getTitle();
                        if (oo.equals(toEventName)) {
                            ArrayList<Diary> diaryArrayList = o.getDiaries();
                            diaryArrayList.add(newDiary);

                            firebase.collection("Other").whereEqualTo("title", toEventName)
                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                }
                            });

                            finish();
                        }
                    }
                } else if (TextUtils.isEmpty(diaryText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the info",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        diaryText = findViewById(R.id.hehehe);

        firebase = FirebaseFirestore.getInstance();
    }
}
