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


public class PersonalFragment extends Fragment implements PersonalAdapter.personalEventListener {

    RecyclerView personalRecView;
    PersonalAdapter pAdapter;

    //Data Arraylists:
    protected ArrayList<String> events;
    protected ArrayList<String> dates;
    protected ArrayList<String > days;

    public static ArrayList<Events> allPersonalEvents;

    private FirebaseFirestore firebase;

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

        ImageButton set = (ImageButton)view.findViewById(R.id.imageButton13);
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

        allPersonalEvents = new ArrayList<Events>();
        intDays = new ArrayList<Integer>();

        presentTitle = (TextView)view.findViewById(R.id.personalEventPresentTitle);
        presentDate = (TextView)view.findViewById(R.id.personalEventPresentDate);
        presentDays = (TextView)view.findViewById(R.id.personalEventPresentDays);
        presentQuote = (TextView)view.findViewById(R.id.personalEventPresentQuote);

        personalRecView = (RecyclerView)view.findViewById(R.id.personalRecylcerView);
        pAdapter = new PersonalAdapter(events, dates, days,this);
        personalRecView.setAdapter(pAdapter);
        personalRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        chosenEvent = new Events();

        getAndPopulateDate();

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

        for(Events e : allPersonalEvents)
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
    public void personalEventOnClick(int position)
    {
        chosenEvent = allPersonalEvents.get(position);

        Intent intent = new Intent(getActivity(), EventProfile.class);
        intent.putExtra("sentEV", chosenEvent.toString());
        startActivity(intent);
    }
}
