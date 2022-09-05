package com.example.ia.EventProfileFolder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ia.AddEvents;
import com.example.ia.EditDiary;
import com.example.ia.R;

public class DiaryProfile extends AppCompatActivity
{

    TextView profileDate;
    TextView profileContent;
    String thisDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_profile);

        //  get the info of the clicked diary title
        Bundle mg= getIntent().getExtras();
        thisDiary = mg.getString("sentDiary");

        profileDate = findViewById(R.id.textView8);
        profileContent = findViewById(R.id.textView9);

        update();

        // if the edit diary icon is clicked, send the current diary’s info to the next screen
        ImageButton button = this.findViewById(R.id.editDiaryButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DiaryProfile.this, EditDiary.class);
                intent.putExtra("editDiary", thisDiary);
                startActivity(intent);
            }
        });
    }

    // this function set up the screen by displaying the title and the content of the diary
    public void update()
    {
        String showDate = thisDiary.substring(thisDiary.indexOf("'", 5 ) + 1 , thisDiary.lastIndexOf("',"));
        profileDate.setText(showDate);

        String conTent = thisDiary.substring(thisDiary.lastIndexOf("=") + 2, thisDiary.lastIndexOf("'"));
        profileContent.setText(conTent);
    }
}
