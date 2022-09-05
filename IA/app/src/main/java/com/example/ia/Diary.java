package com.example.ia;

import android.media.Image;
import android.widget.ImageView;

import java.util.ArrayList;

public class Diary
{

    public String diaryDate;
    public String words;

    public Diary()
    {

    }

    public Diary(String diaryDate, String words)
    {
        this.diaryDate = diaryDate;
        this.words = words;

    }

    @Override
    public String toString()
    {
        return "Diary{" +
                "diaryDate='" + diaryDate + '\'' +
                ", words='" + words + '\'' +
                '}';
    }

    public String getDiaryDate()
    {
        return diaryDate;
    }

    public void setDiaryDate(String diaryDate)
    {
        this.diaryDate = diaryDate;
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
    }
}
