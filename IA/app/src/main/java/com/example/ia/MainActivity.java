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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> motivationalQuote;
    public TextView todayQuote;
    public String haha;
    private FirebaseFirestore firebase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todayQuote = findViewById(R.id.quote);

        firebase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        motivationalQuote = new ArrayList<String>();
        motivationalQuote.add("you got this");
        motivationalQuote.add("keep going!");
        motivationalQuote.add("don't worry, be happy");
        motivationalQuote.add("try your best");


        Random rand = new Random();
        int upperbound = 3;
        int int_random = rand.nextInt(upperbound);
        System.out.println(int_random);

        String thisQuote = motivationalQuote.get(int_random);
        System.out.println(thisQuote);

        todayQuote = findViewById(R.id.quote);
        todayQuote.setText(thisQuote);

//        mAuth.signInWithEmailAndPassword("mjie963@gmail.com", "Mj123456")
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

//        DocumentReference docRef = firebase.collection("User").document("currUser");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("XXX", "DocumentSnapshot data: " + document.getData());
//                        haha = "yes";
//                    } else {
//                        Log.d("XXX", "No such document");
//                        haha = "no";
//                    }
//                } else {
//                    Log.d("XXX", "get failed with ", task.getException());
//                    haha = "no";
//                }
//            }
//        });

        int timeout = 4000; // make the activity visible for 4 seconds

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(MainActivity.this, Passcode.class);
                startActivity(homepage);
//                if(haha.equals("yes"))
//                {
//                    Intent homepage = new Intent(MainActivity.this, Passcode.class);
//                    startActivity(homepage);
//                }
//                else
//                {
//                    Intent homepage = new Intent(MainActivity.this, SetPasscode.class);
//                    startActivity(homepage);
//                }
//
            }
        }, timeout);
    }
}