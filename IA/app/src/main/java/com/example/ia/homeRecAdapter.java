package com.example.ia;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class homeRecAdapter extends RecyclerView.Adapter<homeRecViewHolder>
{
    ArrayList<String> homeNameData;
    ArrayList<String> homeDateData;
    ArrayList<String> homeDaysData;

    public homeRecAdapter(ArrayList<String> names, ArrayList<String> dates, ArrayList<String> days)
    {
        homeNameData = names;
        homeDateData = dates;
        homeDaysData = days;
    }

    @NonNull
    @Override
    public homeRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rec_view,
                parent,false);

        homeRecViewHolder holder = new homeRecViewHolder(myView1);
        return holder;

    }

    public void newData(ArrayList<String> names, ArrayList<String> dates, ArrayList<String> days)
    {
        homeDaysData = days;
        homeDateData = dates;
        homeNameData = names;
    }

    @Override
    public void onBindViewHolder(@NonNull homeRecViewHolder holder, int position)
    {
        holder.homeRecName.setText(homeNameData.get(position));
        holder.homeRecDate.setText(homeDateData.get(position));
        holder.homeRecDays.setText(homeDaysData.get(position));
    }

    @Override
    public int getItemCount() {
        return homeNameData.size();
    }
}
