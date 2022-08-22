package com.example.ia.Fragments.Main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ia.AddEvents;
import com.example.ia.Events;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MainWorkFragment extends Fragment {

    RecyclerView mainRecView;
    MainAdapter mAdapter;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    private FirebaseFirestore firebase;
    protected ArrayList<Events> allMainEvents;
    ArrayList<Integer> intDays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_work, container, false);

        ImageButton ib = (ImageButton)view.findViewById(R.id.addMainEventButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), AddEvents.class);
                // send the name of the vehicle to the editVehicle activity
                intent.putExtra("catMessage", "Main");
                startActivity(intent);
            }
        });

        events = new ArrayList<String>();

        dates = new ArrayList<String>();

        days = new ArrayList<String>();

        firebase = FirebaseFirestore.getInstance();

        allMainEvents = new ArrayList<Events>();
        intDays = new ArrayList<Integer>();

        mainRecView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        mAdapter = new MainAdapter(events, dates, days);
        mainRecView.setAdapter(mAdapter);
        mainRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAndPopulateDate();
        showMostRecent();

        return view;
    }

    public void getAndPopulateDate()
    {
        firebase.collection("Main").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for (DocumentSnapshot ds : task.getResult().getDocuments())
                            {
                                // convert the vehicle documents to Vehicle type
                                Events getEvents = ds.toObject(Events.class);
                                // add them into the arraylist
                                allMainEvents.add(getEvents);
                            }
                            // run through each vehicle in the arraylist to get their model, type, and status
                            for(Events eachEvent : allMainEvents)
                            {
                                String eachEventName = eachEvent.getTitle();
                                events.add(eachEventName);

                                String eachDate = eachEvent.getDate();
                                dates.add(eachDate);

                                String eachDays = eachEvent.getDays();
                                days.add(eachDays);
                            }
                            mAdapter.newDate(events, dates, days);
                            mAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "you don't have any events yet, " +
                                    "go add some", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void showMostRecent()
    {

//        intDays.add(6);
//        intDays.add(9);
//        intDays.add(8);

        for(String d : days)
        {
            System.out.println("III" + d);
            int i = Integer.parseInt(d);
            System.out.println("III" + i);
            intDays.add(i);
        }

//        int minimum = intDays.get(0);
//        for (int i = 1; i < intDays.size(); i++)
//        {
//            if (minimum > intDays.get(i))
//            {
//                minimum = intDays.get(i);
//            }
//        }
//        System.out.println("MMM" + minimum);
    }


}

