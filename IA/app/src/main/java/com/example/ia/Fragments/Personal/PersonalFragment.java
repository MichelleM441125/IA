package com.example.ia.Fragments.Personal;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ia.AddEvents;
import com.example.ia.Fragments.Main.MainAdapter;
import com.example.ia.R;

import java.util.ArrayList;


public class PersonalFragment extends Fragment {

    RecyclerView personalRecView;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

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

        events = new ArrayList<String>();
        events.add("Birthday");
        events.add("Buy fruits");

        dates = new ArrayList<String>();
        dates.add("2022-08-27");
        dates.add("2022-08-31");

        days = new ArrayList<String>();
        days.add("10");
        days.add("15");


        personalRecView = (RecyclerView)view.findViewById(R.id.personalRecylcerView);
        PersonalAdapter pAdapter = new PersonalAdapter(events, dates, days);
        personalRecView.setAdapter(pAdapter);
        personalRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}
