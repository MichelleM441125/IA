package com.example.ia;

import java.util.ArrayList;
import java.util.Date;

public class Events {

    public String title;
    public String date;
    public String quote;
    public String days;
    public String category;
    public ArrayList<Subevent> subEvents;
    public ArrayList<Diary> diaries;

    public Events()
    {

    }


    public Events(String title, String date, String days,String quote)
    {
        this.title = title;
        this.date = date;
        this.days = days;
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Events{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", quote='" + quote + '\'' +
                ", days='" + days + '\'' +
                '}';
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
