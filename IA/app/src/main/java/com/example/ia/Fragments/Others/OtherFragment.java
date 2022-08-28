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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ia.AddEvents;
import com.example.ia.EventProfileFolder.EventProfile;
import com.example.ia.Events;
import com.example.ia.R;
import com.example.ia.passcode.SetPasscode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class OtherFragment extends Fragment implements OthersAdapter.otherEventListener {

    RecyclerView recView;
    OthersAdapter oAdapter;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    private FirebaseFirestore firebase;
    public static ArrayList<Events> allOtherEvents;
    ArrayList<Integer> intDays;

    TextView presentTitle;
    TextView presentDate;
    TextView presentDays;
    TextView presentQuote;

    public Events chosenEvent;

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

        ImageButton set = (ImageButton)view.findViewById(R.id.imageButton10);
        set.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), SetPasscode.class);
                startActivity(intent);
            }
        });

        events = new ArrayList<String>();
        dates = new ArrayList<String>();
        days = new ArrayList<String>();

        firebase = FirebaseFirestore.getInstance();

        allOtherEvents = new ArrayList<Events>();
        intDays = new ArrayList<Integer>();

        presentTitle = (TextView)view.findViewById(R.id.otherEventPresentTitle);
        presentDate = (TextView)view.findViewById(R.id.otherEventPresentDate);
        presentDays = (TextView)view.findViewById(R.id.otherEventPresentDays);
        presentQuote = (TextView)view.findViewById(R.id.otherEventPresentQuote);

        recView = (RecyclerView)view.findViewById(R.id.otherRecyclerView);
        oAdapter = new OthersAdapter(events, dates, days,this);
        recView.setAdapter(oAdapter);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));

        chosenEvent = new Events();

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
                        showMostRecent();
                    }
                });
    }

    public void showMostRecent()
    {

        for(String d : days)
        {
            int i = Integer.parseInt(d);
            intDays.add(i);
        }

        int minimum = intDays.get(0);
        for (int i = 1; i < intDays.size(); i++)
        {
            if (minimum > intDays.get(i))
            {
                minimum = intDays.get(i);
            }
        }

        for(Events e : allOtherEvents)
        {
            String min = e.getDays();
            if(min.equals(String.valueOf(minimum)))
            {
                presentTitle.setText(e.getTitle());
                presentDate.setText(e.getDate());
                presentDays.setText(e.getDays());
                presentQuote.setText(e.getQuote());
            }
        }
    }


    @Override
    public void otherEventOnClick(int position)
    {
        chosenEvent = allOtherEvents.get(position);
        Intent intent = new Intent(getActivity(), EventProfile.class);
        intent.putExtra("sentEV", chosenEvent.toString());
        startActivity(intent);
    }
}

