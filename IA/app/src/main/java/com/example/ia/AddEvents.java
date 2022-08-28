package com.example.ia;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ia.Fragments.Main.MainWorkFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


public class AddEvents extends AppCompatActivity {

    private String newCategoryText;
    private String daysDifference;

    private EditText newNameText;
    private EditText newDateText;
    private EditText newQuoteText;

    private FirebaseFirestore firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);

        Bundle MainMessage = getIntent().getExtras();
        String newCatMessage;
        if (MainMessage != null) {
            newCatMessage = MainMessage.getString("catMessage");
            newCategoryText = newCatMessage;
        }

        newNameText = findViewById(R.id.NewEventNameText);
        newDateText = findViewById(R.id.NewEventDateText);
        newQuoteText = findViewById(R.id.NewEventQuoteText);

        firebase = FirebaseFirestore.getInstance();


    }


    public void addThisNewEvent(View x)
    {
        if(newNameText != null && newDateText != null && newQuoteText != null )
        {
            String nameInput = newNameText.getText().toString();
            String dateInput = newDateText.getText().toString();
            String quoteInput = newQuoteText.getText().toString();
            ArrayList<Diary> newDiaryList = new ArrayList<>();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {

                Calendar c = Calendar.getInstance();
                String t = format.format(c.getTime());

                Date date = format.parse(dateInput);
                Date today = format.parse(t);

                long difference = Math.abs(date.getTime() - today.getTime());
                long differenceDates = difference / (24 * 60 * 60 * 1000);
                daysDifference = Long.toString(differenceDates);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Events aNewEvent = new Events(nameInput, dateInput, daysDifference, quoteInput, newDiaryList);

            firebase.collection(newCategoryText).add(aNewEvent);

            Toast.makeText(getApplicationContext(),"Add Successfully",
                    Toast.LENGTH_SHORT).show();

            finish();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please fill in all the info",
                    Toast.LENGTH_SHORT).show();
        }

    }

}