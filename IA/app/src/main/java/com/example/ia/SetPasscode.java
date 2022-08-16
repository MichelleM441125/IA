package com.example.ia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetPasscode extends AppCompatActivity {

    public EditText newPasscode;
    private FirebaseFirestore firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_passcode);

        newPasscode = findViewById(R.id.passcodeText);
        firebase = FirebaseFirestore.getInstance();

    }

    public void setNewPasscode(View x)
    {
        if (newPasscode.length() != 4 )
        {
            Toast.makeText(getApplicationContext(),"The Passcode Can Only be 4 Numbers",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            String passcode = newPasscode.getText().toString();

            User currUser = new User(passcode, true);

            firebase.collection("User").document("currUser").set(currUser);

            startActivity(new Intent(this, Passcode.class));

        }

    }
}