package com.example.ia.passcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.ia.Home;
import com.example.ia.R;
import com.example.ia.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanks.passcodeview.PasscodeView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

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

        // get the current passcode from firebase
        DocumentReference docRef = firebase.collection("User")
                .document("currUser");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if (task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();

                    // convert the result to String
                    String dddd = document.getData().toString();
                    code = dddd.substring(dddd.indexOf("=")+1, dddd.indexOf("}"));

                    // set the length as 4 digits
                    passcodeView.setPasscodeLength(4)
                            // to set  passcode
                            .setLocalPasscode(code)

                            // set listener to it to check whether passwords has matched or failed
                            .setListener(new PasscodeView.PasscodeViewListener()
                            {
                                @Override
                                public void onFail()
                                {
                                    // show message when Password is incorrect
                                    Toast.makeText(Passcode.this, "Password is wrong!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(String number)
                                {
                                    //  when password is correct user will be directly navigated to next activity
                                    Intent intent_passcode = new Intent(Passcode.this, Home.class);
                                    startActivity(intent_passcode);
                                }
                            });
                } else
                {
                    Log.d("getData", "No such document");
                }
            }
        });
    }
}
