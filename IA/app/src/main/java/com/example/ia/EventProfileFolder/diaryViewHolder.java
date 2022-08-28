package com.example.ia.EventProfileFolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.R;

public class diaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView diaryDateText;
    diaryAdapter.diaryListener diaryListener;

    public diaryViewHolder(@NonNull View itemView, diaryAdapter.diaryListener diaryListener1) {
        super(itemView);

        diaryDateText = itemView.findViewById(R.id.dddd);
        diaryListener = diaryListener1;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        diaryListener.diaryOnClick(getAdapterPosition());
    }
}
