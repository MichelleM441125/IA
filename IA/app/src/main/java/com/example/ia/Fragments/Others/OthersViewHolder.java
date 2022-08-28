package com.example.ia.Fragments.Others;
import com.example.ia.Home;
import com.example.ia.R;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OthersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    protected TextView otherEventText;
    protected TextView otherDaysText;
    protected TextView otherDateText;
    OthersAdapter.otherEventListener otherListener;

    public OthersViewHolder(@NonNull View itemView, OthersAdapter.otherEventListener otherListener1) {
        super(itemView);

        otherEventText = itemView.findViewById(R.id.othersEvent);
        otherDateText = itemView.findViewById(R.id.othersDate);
        otherDaysText = itemView.findViewById(R.id.othersDays);
        otherListener = otherListener1;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        otherListener.otherEventOnClick(getAdapterPosition());


    }
}
