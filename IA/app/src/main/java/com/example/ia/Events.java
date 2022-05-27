package com.example.ia;

import java.util.ArrayList;
import java.util.Date;

public class Events {

    public String title;
    public Date date;
    public String quote;
    public ArrayList<Subevent> subEvents;
    public ArrayList<Diary> diaries;

    public Events(String title, Date date, String quote)
    {
        this.title = title;
        this.date = date;
        this.quote = quote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
