package com.example.ia.Fragments.Others;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.Fragments.Main.MainViewHolder;
import com.example.ia.Fragments.Personal.PersonalAdapter;
import com.example.ia.Fragments.Personal.PersonalViewHolder;
import com.example.ia.R;

import java.util.ArrayList;

public class OthersAdapter extends RecyclerView.Adapter<OthersViewHolder>
{
    ArrayList<String> oEvents;
    ArrayList<String> oDates;
    ArrayList<String> oDays;
    private otherEventListener otherListener;

    public OthersAdapter(ArrayList events, ArrayList dates, ArrayList days, otherEventListener otherListener1)
    {
        oEvents = events;
        oDates = dates;
        oDays = days;
        otherListener = otherListener1;
    }

    @NonNull
    @Override
    public OthersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View otherView = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_view, parent, false);
        OthersViewHolder othersViewHolder = new OthersViewHolder(otherView, otherListener);

        return othersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OthersViewHolder holder, int position)
    {
        holder.otherEventText.setText(oEvents.get(position));
        holder.otherDateText.setText(oDates.get(position));
        holder.otherDaysText.setText(oDays.get(position));

    }

    @Override
    public int getItemCount() {
        return oDates.size();
    }

    public void newDate(ArrayList eventData, ArrayList dateData, ArrayList dayData)
    {
        oEvents = eventData;
        oDates = dateData;
        oDays = dayData;
    }

    public interface  otherEventListener
    {
        void otherEventOnClick(int position);
    }
}
