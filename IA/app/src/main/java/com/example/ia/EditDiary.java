package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ia.EventProfileFolder.EventProfile;
import com.example.ia.Fragments.Main.MainWorkFragment;
import com.example.ia.Fragments.Others.OtherFragment;
import com.example.ia.Fragments.Personal.PersonalFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EditDiary extends AppCompatActivity {

    EditText newContent;
    TextView originalContent;
    String editingDiary;
    String oC;
    String nC;
    Events ev;
    private FirebaseFirestore firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);

        newContent = findViewById(R.id.newContentBox);
        originalContent = findViewById(R.id.originalContent);

        Bundle mg= getIntent().getExtras();
        editingDiary = mg.getString("editDiary");
        System.out.println("editingDiary " + editingDiary);

        oC = editingDiary.substring(editingDiary.lastIndexOf("=") + 2, editingDiary.lastIndexOf("'"));
        System.out.println("original " + oC);

        originalContent.setText(oC);

        firebase = FirebaseFirestore.getInstance();

        ImageButton button = this.findViewById(R.id.doneEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                updateMainDiary();
                Intent intent = new Intent(EditDiary.this, Home.class);
                intent.putExtra("sentEV", ev.toString());
                startActivity(intent);
            }
        });

    }

    public void updateMainDiary()
    {
        if (TextUtils.isEmpty(newContent.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Please fill in new content", Toast.LENGTH_SHORT).show();
        }
        else
        {
            nC = newContent.getText().toString();

            // for main category
            for (Events e : MainWorkFragment.allMainEvents)
            {
                ArrayList<Diary> mDList = e.getDiaries();
                String meTitle = e.getTitle();

                for (Diary mD : mDList)
                {
                    String content = mD.getWords();

                    if (oC.equals(content))
                    {
                        ev = e;
                        mD.setWords(nC);
//                        mDList.add(mD);
                        e.setDiaries(mDList);

                        System.out.println("newList " + mDList.toString());

                        firebase.collection("Main").whereEqualTo("title", meTitle)
                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                    // update the information with the ID of the document
                                    String ID = ds.getId();
                                    firebase.collection("Main").document(ID)
                                            .update("diaries", mDList);
                                }
                            }
                        });
                    } else {
                        System.out.println("can't update");
                    }

                }
            }

//             for (Events p : PersonalFragment.allPersonalEvents)
//             {
//                 ArrayList<Diary> pDList = p.getDiaries();
//                 String peTitle = p.getTitle();
//
//                 for (Diary pD : pDList)
//                 {
//                     String pContent = pD.getWords();
//                     if (oC.equals(pContent))
//                     {
//                         ev = p;
//                         pD.setWords(nC);
//                         p.setDiaries(pDList);
//
//                         System.out.println("newList " + pDList.toString());
//
//                         firebase.collection("Personal").whereEqualTo("title", peTitle)
//                                 .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
//                         {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task)
//                                {
//                                    for (DocumentSnapshot ds : task.getResult().getDocuments())
//                                    {
//                                        // update the information with the ID of the document
//                                        String ID = ds.getId();
//                                        firebase.collection("Personal").document(ID)
//                                                .update("diaries", pDList);
//                                    }
//                                }
//                            });
//                     }
//                     else
//                     {
//                         System.out.println("can't update");
//                     }
//                 }
//             }

//                // for Other category
//                for (Events o : OtherFragment.allOtherEvents)
//                {
//                    ArrayList<Diary> oDList = o.getDiaries();
//                    String oeTitle = o.getTitle();
//
//                    for (Diary oD : oDList)
//                    {
//                        String oContent = oD.getWords();
//
//                        if (oC.equals(oContent))
//                        {
//                            ev = o;
//                            oD.setWords(nC);
//                            o.setDiaries(oDList);
//
//                            System.out.println("newList " + oDList.toString());
//
//                            firebase.collection("Other").whereEqualTo("title", oeTitle)
//                                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    for (DocumentSnapshot ds : task.getResult().getDocuments()) {
//                                        // update the information with the ID of the document
//                                        String ID = ds.getId();
//                                        firebase.collection("Other").document(ID)
//                                                .update("diaries", oDList);
//                                    }
//                                }
//                            });
//                        } else {
//                            System.out.println("can't update");
//                        }
//
//                    }
//                }

        }
    }

}