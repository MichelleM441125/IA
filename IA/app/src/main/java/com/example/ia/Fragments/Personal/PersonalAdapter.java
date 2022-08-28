package com.example.ia.Fragments.Personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.Fragments.Main.MainViewHolder;
import com.example.ia.R;

import java.util.ArrayList;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalViewHolder>
{
    ArrayList<String> pEvents;
    ArrayList<String> pDates;
    ArrayList<String> pDays;
    private personalEventListener personalListener;

    public PersonalAdapter(ArrayList events, ArrayList dates, ArrayList days, personalEventListener personalListener1)
    {
        pEvents = events;
        pDates = dates;
        pDays = days;
        personalListener = personalListener1;
    }


    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View personalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_view, parent, false);
        PersonalViewHolder personalHolder = new PersonalViewHolder(personalView, personalListener);

        return  personalHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalViewHolder holder, int position)
    {
        holder.personalEventText.setText(pEvents.get(position));
        holder.personalDateText.setText(pDates.get(position));
        holder.personalDaysText.setText(pDays.get(position));

    }

    @Override
    public int getItemCount() {
        return pEvents.size();
    }

    public void newDate(ArrayList eventData, ArrayList dateData, ArrayList dayData)
    {
        pEvents = eventData;
        pDates = dateData;
        pDays = dayData;
    }

    public interface personalEventListener
    {
        void personalEventOnClick(int position);
    }

}
