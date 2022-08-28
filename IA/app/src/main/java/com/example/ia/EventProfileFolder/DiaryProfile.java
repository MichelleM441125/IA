package com.example.ia.EventProfileFolder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ia.R;

public class DiaryProfile extends AppCompatActivity {

    TextView profileDate;
    TextView profileContent;
    String thisDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_profile);

        Bundle mg= getIntent().getExtras();
        thisDiary = mg.getString("sentDiary");
        System.out.print("SSSSS" + thisDiary);

        profileDate = findViewById(R.id.textView8);
        profileContent = findViewById(R.id.textView9);

        update();

    }

    public void update()
    {
        String showDate = thisDiary.substring(thisDiary.indexOf("'", 5 ) + 1 , thisDiary.lastIndexOf("',"));
        profileDate.setText(showDate);

        String conTent = thisDiary.substring(thisDiary.lastIndexOf("=") + 1, thisDiary.lastIndexOf("'"));
        profileContent.setText(conTent);

    }
}