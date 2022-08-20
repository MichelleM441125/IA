package com.example.ia.Fragments.Others;

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


public class OtherFragment extends Fragment {

    RecyclerView recView;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        ImageButton image = (ImageButton)view.findViewById(R.id.AddNewOtherEventButton);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), AddEvents.class);
                // send the name of the vehicle to the editVehicle activity
                intent.putExtra("catMessage", "Other");
                startActivity(intent);
            }
        });

        events = new ArrayList<String>();
        events.add("ED");
        events.add("Results");

        dates = new ArrayList<String>();
        dates.add("2022-11-01");
        dates.add("2022-12-15");

        days = new ArrayList<String>();
        days.add("76");
        days.add("94");


        recView = (RecyclerView)view.findViewById(R.id.otherRecyclerView);
        OthersAdapter oAdapter = new OthersAdapter(events, dates, days);
        recView.setAdapter(oAdapter);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}

