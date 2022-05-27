package com.example.ia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class AddNewEvent extends AppCompatActivity {

    private EditText newEventTitle;
    private EditText newEventDate;
    private EditText newEventQuote;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);

        newEventTitle = findViewById(R.id.NewEventTitleText);
        newEventDate = findViewById(R.id.NewEventDateText);
        newEventQuote = findViewById(R.id.NewEventQuoteText);

        mAuth = FirebaseAuth.getInstance();
        firebase = FirebaseFirestore.getInstance();
    }

    public void addNewEvent(View x)
    {
        if(newEventTitle != null && newEventDate != null)
        {
            String newTitle = newEventTitle.getText().toString();
            Date newDate = (Date) newEventDate.getText();
            String newQuote = newEventQuote.getText().toString();

            Events newEvent = new Events(newTitle, newDate, newQuote);

            firebase.collection("Event").add(newEvent);

        }
        else if(newEventTitle == null)
        {
            Toast.makeText(getApplicationContext(),"Please Enter the Title of the New Event",
                    Toast.LENGTH_SHORT).show();
        }
        else if(newEventDate == null)
        {
            Toast.makeText(getApplicationContext(),"Please Enter the Due Date of the New Event",
                    Toast.LENGTH_SHORT).show();
        }
    }



}