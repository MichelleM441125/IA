package com.example.ia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class MainWorkFragment extends Fragment {

    FirebaseFirestore firebase;

    TextView homeQuote;
    TextView homeEvent;
    TextView homeDays;
    TextView homeDate;

    RecyclerView homeRec;
    homeRecAdapter homeAdapter;

    ArrayList<Events> events;

    ArrayList<String> EventNames;
    ArrayList<String> EventDates;
    ArrayList<String> EventDays;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_work, container, false);

//        firebase = FirebaseFirestore.getInstance();
//
//        homeQuote = homeQuote.findViewById(R.id.quoteText);
//        homeEvent = homeEvent.findViewById(R.id.homeEventNameText);
//        homeDays =  homeDays.findViewById(R.id.homeDaysText);
//        homeDate = homeDate.findViewById(R.id.homeDateText);
//
//        EventNames = new ArrayList<String>();
//        EventDates = new ArrayList<String>();
//        EventDays = new ArrayList<String>();
//
//        EventNames.add("Bio Test");
//        EventNames.add("Psych IA Intro");
//        EventNames.add("CS Cri C");
//
//        EventDates.add("2022-06-03");
//        EventDates.add("2022-06-02");
//        EventDates.add("2022-06-01");
//
//        EventDays.add("3");
//        EventDays.add("2");
//        EventDays.add("1");

//        homeRec = homeRec.findViewById(R.id.homeRecyclerView);
//
//        homeAdapter = new homeRecAdapter(EventNames, EventDates, EventDays);
//
//        homeRec.setAdapter(homeAdapter);
//        homeRec.setLayoutManager(new LinearLayoutManager(this));
//
//        getAndOrganizeData();
//        populateData();
    }

    public void getAndOrganizeData() {
        // get all the vehicles that the current user owns
        firebase.collection("Event").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                                // convert the Event documents to Event type
                                Events getEvent = ds.toObject(Events.class);
                                // add them into the arraylist
                                events.add(getEvent);
                            }

                            Events thisEvent = new Events();
                            for (int i = 0; i < events.size(); i++) {
                                for (int j = i + 1; j < events.size(); j++) {
                                    int day1 = Integer.parseInt(events.get(i).getDays());
                                    int day2 = Integer.parseInt(events.get(j).getDays());

                                    if (day1 > day2) {
                                        thisEvent = events.get(i);
                                        events.set(i, events.get(j));
                                        events.set(j, thisEvent);
                                    }
                                }
                            }
                        }
                    }
                });

    }

//    public void populateData()
//    {
//        // run through each event in the arraylist to get their info
//        for(Events eachEvent : events)
//        {
//            String eachName = eachEvent.getTitle();
//            EventNames.add(eachName);
//
//            String eachDate = eachEvent.getDate();
//            EventDates.add(eachDate);
//
//            String eachDay = eachEvent.getDays();
//            EventDays.add(eachDay);
//        }
//
//
//        homeAdapter = new homeRecAdapter(EventNames, EventDates, EventDays);
//        homeRec.setAdapter(homeAdapter);
//        homeRec.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//
}

