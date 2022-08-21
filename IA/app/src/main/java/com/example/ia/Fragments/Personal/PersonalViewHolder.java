package com.example.ia.Fragments.Personal;

import com.example.ia.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalViewHolder extends RecyclerView.ViewHolder  {

    protected TextView personalEventText;
    protected TextView personalDaysText;
    protected TextView personalDateText;


    public PersonalViewHolder(@NonNull View itemView) {
        super(itemView);

        personalEventText = itemView.findViewById(R.id.personalEvent);
        personalDateText = itemView.findViewById(R.id.personalDate);
        personalDaysText = itemView.findViewById(R.id.personalDays);
    }
}