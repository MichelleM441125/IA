package com.example.ia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEvents extends AppCompatActivity {

    private String newCategoryText;
    private EditText newNameText;
    private EditText newDateText;
    private EditText newQuoteText;

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

    }

    public void addThisNewEvent(View x)
    {
        System.out.println(newCategoryText);
    }

}