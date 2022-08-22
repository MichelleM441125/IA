package com.example.ia.Fragments.Personal;


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
import com.example.ia.Fragments.Main.MainAdapter;
import com.example.ia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class PersonalFragment extends Fragment {

    RecyclerView personalRecView;
    PersonalAdapter pAdapter;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    protected ArrayList<Events> allPersonalEvents;

    private FirebaseFirestore firebase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ImageButton button = (ImageButton)view.findViewById(R.id.AddNewPersonalEventButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), AddEvents.class);
                // send the name of the vehicle to the editVehicle activity
                intent.putExtra("catMessage", "Personal");
                startActivity(intent);
            }
        });

        firebase = FirebaseFirestore.getInstance();

        events = new ArrayList<String>();

        dates = new ArrayList<String>();

        days = new ArrayList<String>();


        allPersonalEvents = new ArrayList<Events>();

        System.out.println(days);

        personalRecView = (RecyclerView)view.findViewById(R.id.personalRecylcerView);
        pAdapter = new PersonalAdapter(events, dates, days);
        personalRecView.setAdapter(pAdapter);
        personalRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAndPopulateDate();

        System.out.println(events + "EEE");

        return view;
    }

    public void getAndPopulateDate()
    {
        firebase.collection("Personal").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            for (DocumentSnapshot ds : task.getResult().getDocuments())
                            {

                                Events getEvents = ds.toObject(Events.class);
                                allPersonalEvents.add(getEvents);
                            }

                            for(Events eachEvent : allPersonalEvents)
                            {
                                System.out.println(eachEvent.toString() + "ZZZ");

                                String eachEventName = eachEvent.getTitle();
                                events.add(eachEventName);

                                String eachDate = eachEvent.getDate();
                                dates.add(eachDate);

                                String eachDays = eachEvent.getDays();
                                days.add(eachDays);
                            }
                            pAdapter.newDate(events, dates, days);
                            pAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "you don't have any events yet, " +
                                    "go add some", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
