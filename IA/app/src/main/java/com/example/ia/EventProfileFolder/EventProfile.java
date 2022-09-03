package com.example.ia.EventProfileFolder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ia.AddNewDiary;
import com.example.ia.Diary;
import com.example.ia.Events;
import com.example.ia.Fragments.Main.MainWorkFragment;
import com.example.ia.Fragments.Others.OtherFragment;
import com.example.ia.Fragments.Personal.PersonalFragment;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventProfile extends AppCompatActivity implements diaryAdapter.diaryListener {

    RecyclerView diaryRecView;
    diaryAdapter dAdapter;
    ArrayList<String> dates;
    ArrayList<Diary> d;

    String chosenEV;

    String title1;
    String date1;
    String day1;

    TextView chosenTitle;
    TextView chosenDate;
    TextView chosenDays;

    ArrayList<Events> allEvents;

    FirebaseFirestore firebase;

    Diary chosenDiary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);

        dates = new ArrayList<String>();
        d = new ArrayList<Diary>();
        diaryRecView = (RecyclerView)this.findViewById(R.id.diaryRec);
        dAdapter = new diaryAdapter(dates, this);
        diaryRecView.setAdapter(dAdapter);
        diaryRecView.setLayoutManager(new LinearLayoutManager(this));

        Bundle mg= getIntent().getExtras();
        chosenEV = mg.getString("sentEV");

        chosenTitle = findViewById(R.id.EventProfileName);
        chosenDate = findViewById(R.id.EventProfileDate);
        chosenDays = findViewById(R.id.EventProfileDays);

        allEvents = new ArrayList<Events>();

        firebase = FirebaseFirestore.getInstance();

        splitAndDisplay();

        ImageButton b = (ImageButton)this.findViewById(R.id.AddNewDiaryButtom);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent it = new Intent(EventProfile.this, AddNewDiary.class);
                it.putExtra("info", title1);
                startActivity(it);
            }
        });

        ImageButton button = (ImageButton)this.findViewById(R.id.imageButton4);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // for Main Events
                for(Events e : MainWorkFragment.allMainEvents)
                {
                    String deleteTitle = e.getTitle();

                    if(deleteTitle.equals(title1))
                    {
                        firebase.collection("Main").whereEqualTo("title", title1)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                        String idd = ds.getId();
                                        firebase.collection("Main").document(idd)
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.d("delete", "DocumentSnapshot successfully deleted!");
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w("delete", "Error deleting document", e);
                                                    }
                                                });
                                    }
                                }
                            }
                        });
                        finish();
                    }

                }

//                // for Other Events
//                for(Events o : OtherFragment.allOtherEvents)
//                {
//                    String deleteTitle = o.getTitle();
//
//                    if(deleteTitle.equals(title1))
//                    {
//                        firebase.collection("Other").whereEqualTo("title", title1)
//                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
//                                        String idd = ds.getId();
//
//                                        firebase.collection("Other").document(idd)
//                                                .delete()
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        Log.d("delete", "DocumentSnapshot successfully deleted!");
//                                                    }
//                                                })
//                                                .addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.w("delete", "Error deleting document", e);
//                                                    }
//                                                });
//                                    }
//                                }
//                            }
//                        });
//
//                    }
//
//                }
//
//                // for Personal Events
//
//                for(Events p : PersonalFragment.allPersonalEvents)
//                {
//                    String deleteTitle = p.getTitle();
//
//                    if(deleteTitle.equals(title1))
//                    {
//                        firebase.collection("Personal").whereEqualTo("title", title1)
//                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
//                                        String idd = ds.getId();
//                                        firebase.collection("Personal").document(idd)
//                                                .delete()
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        Log.d("delete", "DocumentSnapshot successfully deleted!");
//                                                    }
//                                                })
//                                                .addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.w("delete", "Error deleting document", e);
//                                                    }
//                                                });
//                                    }
//                                }
//                            }
//                        });
//
//                    }
//
//                }

            }
        });
    }

    public void getAndPopulateDataMain()
    {
        firebase.collection("Main").whereEqualTo("title", title1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot ds : task.getResult().getDocuments()) {

                                Events getEvents = ds.toObject(Events.class);
                                d = getEvents.getDiaries();
                                for (Diary dd : d) {
                                    dates.add(dd.getDiaryDate());
                                }
                                dAdapter.newData(dates);
                                dAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "you don't have any diary yet, " +
                                    "go add some", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public void getAndPopulateDataPersonal() {
        firebase.collection("Personal").whereEqualTo("title", title1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot ds : task.getResult().getDocuments()) {

                                Events getEvents = ds.toObject(Events.class);
                                d = getEvents.getDiaries();
                                for (Diary dd : d) {
                                    dates.add(dd.getDiaryDate());
                                }
                                dAdapter.newData(dates);
                                dAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "you don't have any diary yet, " +
                                    "go add some", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void getAndPopulateDataOther()
    {

        firebase.collection("Other").whereEqualTo("title", title1).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for (DocumentSnapshot ds : task.getResult().getDocuments())
                            {

                                Events getEvents = ds.toObject(Events.class);
                                d = getEvents.getDiaries();
                                for(Diary dd : d)
                                {
                                    dates.add(dd.getDiaryDate());
                                }

                                dAdapter.newData(dates);
                                dAdapter.notifyDataSetChanged();

                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "you don't have any diary yet, " +
                                    "go add some", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    //Events{title='CS IA Due', date='2022-09-02', quote='You should've done more', days='10'}
    public void splitAndDisplay()
    {
        title1 = chosenEV.substring(chosenEV.indexOf("'", 5 ) + 1 , chosenEV.lastIndexOf("',"));
        chosenTitle.setText(title1);

        date1 = chosenEV.substring(chosenEV.indexOf(",") + 8 , chosenEV.lastIndexOf("';"));
        chosenDate.setText(date1);

        day1 = chosenEV.substring(chosenEV.lastIndexOf(":")+ 8, chosenEV.lastIndexOf("'"));
        chosenDays.setText(day1);

        getAndPopulateDataMain();
        getAndPopulateDataPersonal();
        getAndPopulateDataOther();
    }

    @Override
    public void diaryOnClick(int position)
    {
        chosenDiary = d.get(position);
        System.out.println("chosenD" + chosenDiary.toString());
        Intent intent = new Intent(EventProfile.this, DiaryProfile.class);
        intent.putExtra("sentDiary", chosenDiary.toString());
        startActivity(intent);
    }
}