package com.example.ia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private String newDays;


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

    @Override
    public void onBackPressed() {
        // this method is used to finish the activity
        // when user enters the correct password
        this.finishAffinity();
    }

    public void addNewEvent(View x) throws ParseException {
        if(newEventTitle == null || newEventDate == null || newEventQuote == null)
        {
            Toast.makeText(getApplicationContext(),"Please Enter all the info",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            String newTitle = newEventTitle.getText().toString();
            String newDate = newEventDate.getText().toString();
            String newQuote = newEventQuote.getText().toString();

            SimpleDateFormat dates = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            String date = dates.format(c.getTime());

            Date date1 = dates.parse(newDate);
            Date date2 = dates.parse(date);

            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            newDays = Long.toString(differenceDates);

            Events newEvent = new Events(newTitle, newDate, newQuote, newDays);

            firebase.collection("Event").add(newEvent);

            Intent goHome = new Intent(this, Home.class);
            startActivity(goHome);
        }

    }

    public void goBackHome(View x)
    {
        Intent goHome = new Intent(this, Home.class);
        startActivity(goHome);

    }

}