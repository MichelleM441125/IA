package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ia.passcode.Passcode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    public ArrayList<String> motivationalQuote;
    public TextView todayQuote;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayQuote = findViewById(R.id.quote);

        motivationalQuote = new ArrayList<String>();
        // add the quotes to the quote arraylist
        motivationalQuote.add("you got this");
        motivationalQuote.add("keep going!");
        motivationalQuote.add("don't worry, be happy");
        motivationalQuote.add("try your best");

        // set up a random number generator
        Random rand = new Random();
        int upperbound = 3;
        int int_random = rand.nextInt(upperbound);

        // set and display the chosen quote
        String thisQuote = motivationalQuote.get(int_random);
        todayQuote = findViewById(R.id.quote);
        todayQuote.setText(thisQuote);

        // make the activity visible for 3 seconds
        int timeout = 3000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {

            @Override
            public void run()
            {
                // auto transit to home screen
                finish();
                Intent homepage = new Intent(MainActivity.this, Passcode.class);
                startActivity(homepage);

            }
        }, timeout);
    }
}

