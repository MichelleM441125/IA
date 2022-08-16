package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanks.passcodeview.PasscodeView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Passcode extends AppCompatActivity {

    public PasscodeView passcodeView;
    public String code;
    public FirebaseFirestore firebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        firebase = FirebaseFirestore.getInstance();

        passcodeView = findViewById(R.id.passcodeview);

        DocumentReference docRef = firebase.collection("User")
                .document("currUser");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    User thisUser = document.toObject(User.class);
                    Log.d("getData", "DocumentSnapshot data: " + document.getData());

                    code = thisUser.getPasscode();

                } else {
                    Log.d("getData", "No such document");
                }
            }
        });


        // to set length of password as here
        // we have set the length as 4 digits
        passcodeView.setPasscodeLength(4)
                // to set  passcode
                .setLocalPasscode("0713")

                // to set listener to it to check whether
                // passwords has matched or failed
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        // to show message when Password is incorrect
                        Toast.makeText(Passcode.this, "Password is wrong!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        // here is used so that when password
                        // is correct user will be directly navigated to next activity
                        Intent intent_passcode = new Intent(Passcode.this, Home.class);
                        startActivity(intent_passcode);
                    }
                });
        }
    }







