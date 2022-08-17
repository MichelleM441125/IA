package com.example.ia.Fragments.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

