
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


public class MainWorkFragment extends Fragment implements MainAdapter.mainEventListener {

    RecyclerView mainRecView;
    MainAdapter mAdapter;

    //Data Arraylists:
    ArrayList<String> events;
    ArrayList<String> dates;
    ArrayList<String > days;

    FirebaseFirestore firebase;
    public static ArrayList<Events> allMainEvents;
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
        View view = inflater.inflate(R.layout.fragment_main_work, container, false);

        ImageButton ib = (ImageButton)view.findViewById(R.id.addMainEventButton);
        ib.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), AddEvents.class);
                // send the name of the category to the addEvent activity
                intent.putExtra("catMessage", "Main");
                startActivity(intent);
            }
        });

        // set onClickListener to the setting button
        ImageButton set = (ImageButton)view.findViewById(R.id.settingButton);
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

        allMainEvents = new ArrayList<Events>();
        intDays = new ArrayList<Integer>();

        presentTitle = (TextView)view.findViewById(R.id.mainEventPresentTitle);
        presentDate = (TextView)view.findViewById(R.id.mainEventPresentDate);
        presentDays = (TextView)view.findViewById(R.id.mainEventPresentDays);
        presentQuote = (TextView)view.findViewById(R.id.mainEventPresentQuote);

        mainRecView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        mAdapter = new MainAdapter(events, dates, days, this);
        mainRecView.setAdapter(mAdapter);
        mainRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        chosenEvent = new Events();

        getAndPopulateData();

        return view;
    }


    public void getAndPopulateData()
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
                                // convert the documents to Events type
                                Events getEvents = ds.toObject(Events.class);
                                // add them into the arraylist
                                allMainEvents.add(getEvents);
                            }
                            // run through each Event in the arraylist to get their title, date, and days
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
                        showMostRecent();
                    }
                });


    }

    // this function find the most recent event and display it.
    public void showMostRecent()
    {
        // add all the remaining days into one arrayList
        for(String d : days)
        {
            int i = Integer.parseInt(d);
            intDays.add(i);
        }

        // find the minimum number in that arrayList by comparing each number to the “minimum”
        int minimum = intDays.get(0);
        for (int i = 1; i < intDays.size(); i++)
        {
            if (minimum > intDays.get(i))
            {
                minimum = intDays.get(i);
            }
        }

        // run through all the events’ remaining day to find the one that mach with “minimum”
        for(Events e : allMainEvents)
        {
            String min = e.getDays();
            if(min.equals(String.valueOf(minimum)))
            {
                // display that event
                presentTitle.setText(e.getTitle());
                presentDate.setText(e.getDate());
                presentDays.setText(e.getDays());
                presentQuote.setText(e.getQuote());
            }
        }
    }

    @Override
    public void mainEventOnClick(int position)
    {
        // onClickListener: when the event in the recyclerView is clicked, send its info to the next screen
        chosenEvent = allMainEvents.get(position);
        System.out.println(chosenEvent.toString());
        Intent intent = new Intent(getActivity(), EventProfile.class);
        intent.putExtra("sentEV", chosenEvent.toString());
        startActivity(intent);
    }
}
