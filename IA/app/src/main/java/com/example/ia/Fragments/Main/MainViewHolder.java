package com.example.ia.Fragments.Main;

import com.example.ia.Home;
import com.example.ia.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    protected TextView mainEventText;
    protected TextView mainDaysText;
    protected TextView mainDateText;


    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        mainEventText = itemView.findViewById(R.id.MainEvent);
        mainDateText = itemView.findViewById(R.id.MainDate);
        mainDaysText = itemView.findViewById(R.id.MainDay);
    }
}
