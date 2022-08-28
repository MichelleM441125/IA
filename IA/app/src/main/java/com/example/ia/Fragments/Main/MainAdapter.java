package com.example.ia.Fragments.Main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>
{
    ArrayList<String> mEvents;
    ArrayList<String> mDates;
    ArrayList<String> mDays;
    private mainEventListener mainListener;

    public MainAdapter(ArrayList events, ArrayList dates, ArrayList days, mainEventListener mainListener1)
    {
        mEvents = events;
        mDates = dates;
        mDays = days;
        mainListener = mainListener1;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mainView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_view, parent, false);
        MainViewHolder mainHolder = new MainViewHolder(mainView, mainListener);

        return  mainHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position)
    {
        holder.mainEventText.setText(mEvents.get(position));
        holder.mainDateText.setText(mDates.get(position));
        holder.mainDaysText.setText(mDays.get(position));
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void newDate(ArrayList eventData, ArrayList dateData, ArrayList dayData)
    {
        mEvents = eventData;
        mDates = dateData;
        mDays = dayData;
    }

    public interface mainEventListener
    {
        void mainEventOnClick(int position);
    }

}
