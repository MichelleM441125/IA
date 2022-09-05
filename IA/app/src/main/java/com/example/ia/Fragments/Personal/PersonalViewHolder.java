package com.example.ia.Fragments.Personal;

import com.example.ia.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    protected TextView personalEventText;
    protected TextView personalDaysText;
    protected TextView personalDateText;
    PersonalAdapter.personalEventListener personalListener;


    public PersonalViewHolder(@NonNull View itemView, PersonalAdapter.personalEventListener personalListener1)
    {
        super(itemView);

        personalEventText = itemView.findViewById(R.id.personalEvent);
        personalDateText = itemView.findViewById(R.id.personalDate);
        personalDaysText = itemView.findViewById(R.id.personalDays);
        personalListener = personalListener1;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        personalListener.personalEventOnClick(getAdapterPosition());

    }
}
