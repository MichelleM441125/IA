package com.example.ia.Fragments.Main;

import com.example.ia.Home;
import com.example.ia.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected TextView mainEventText;
    protected TextView mainDaysText;
    protected TextView mainDateText;
    MainAdapter.mainEventListener mainListener;


    public MainViewHolder(@NonNull View itemView, MainAdapter.mainEventListener mainListener1) {
        super(itemView);

        mainEventText = itemView.findViewById(R.id.MainEvent);
        mainDateText = itemView.findViewById(R.id.MainDate);
        mainDaysText = itemView.findViewById(R.id.MainDay);
        mainListener = mainListener1;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        mainListener.mainEventOnClick(getAdapterPosition());

    }
}
