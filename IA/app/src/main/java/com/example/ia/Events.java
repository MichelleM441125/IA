package com.example.ia;

import java.util.ArrayList;
import java.util.Date;

public class Events {

    public String title;
    public String date;
    public String quote;
    public String days;
    public ArrayList<Subevent> subEvents;
    public ArrayList<Diary> diaries;

    public Events()
    {

    }


    public Events(String title, String date, String quote, String days)
    {
        this.title = title;
        this.date = date;
        this.quote = quote;
        this.days = days;
    }

    public String getDays() {
        return days;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public ArrayList<Subevent> getSubEvents() {
        return subEvents;
    }

    public void setSubEvents(ArrayList<Subevent> subEvents) {
        this.subEvents = subEvents;
    }

    public ArrayList<Diary> getDiaries() {
        return diaries;
    }

    public void setDiaries(ArrayList<Diary> diaries) {
        this.diaries = diaries;
    }
}
