package com.example.ia.Fragments.Others;

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


public class OtherFragment extends Fragment {

    RecyclerView recView;
    OthersAdapter oAdapter;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    private FirebaseFirestore firebase;
    protected ArrayList<Events> allOtherEvents;

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

        dates = new ArrayList<String>();

        days = new ArrayList<String>();

        recView = (RecyclerView)view.findViewById(R.id.otherRecyclerView);
        oAdapter = new OthersAdapter(events, dates, days);
        recView.setAdapter(oAdapter);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebase = FirebaseFirestore.getInstance();

        allOtherEvents = new ArrayList<Events>();

        getAndPopulateDate();

        return view;
    }

    public void getAndPopulateDate()
    {
        firebase.collection("Other").get()
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
                                allOtherEvents.add(getEvents);
                            }
                            // run through each vehicle in the arraylist to get their model, type, and status
                            for(Events eachEvent : allOtherEvents)
                            {
                                String eachEventName = eachEvent.getTitle();
                                events.add(eachEventName);

                                String eachDate = eachEvent.getDate();
                                dates.add(eachDate);

                                String eachDays = eachEvent.getDays();
                                days.add(eachDays);
                            }
                            oAdapter.newDate(events, dates, days);
                            oAdapter.notifyDataSetChanged();
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

