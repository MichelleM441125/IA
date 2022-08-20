package com.example.ia.Fragments.Main;

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
import com.example.ia.R;

import java.util.ArrayList;


public class MainWorkFragment extends Fragment {

    RecyclerView mainRecView;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

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
        events.add("Death");
        events.add("SAT");

        dates = new ArrayList<String>();
        dates.add("2022-08-22");
        dates.add("2022-08-27");

        days = new ArrayList<String>();
        days.add("5");
        days.add("10");


        mainRecView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        MainAdapter mAdapter = new MainAdapter(events, dates, days);
        mainRecView.setAdapter(mAdapter);
        mainRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}

