package com.example.ia;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class homeRecViewHolder extends RecyclerView.ViewHolder {

    protected TextView homeRecName;
    protected TextView homeRecDate;
    protected TextView homeRecDays;

    public homeRecViewHolder(@NonNull View itemView) {
        super(itemView);

        homeRecName = itemView.findViewById(R.id.homeRecNameText);
        homeRecDate = itemView.findViewById(R.id.homeRecDateText);
        homeRecDays = itemView.findViewById(R.id.homeRecDaysText);
    }
}
