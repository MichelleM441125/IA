package com.example.ia.EventProfileFolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ia.Fragments.Main.MainViewHolder;
import com.example.ia.R;

import java.util.ArrayList;

public class diaryAdapter extends RecyclerView.Adapter<diaryViewHolder>
{
    ArrayList<String> dDates;
    private diaryListener diaryListener;

    public diaryAdapter(ArrayList dates, diaryListener diaryListener1)
    {
        dDates = dates;
        diaryListener = diaryListener1;
    }


    @NonNull
    @Override
    public diaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View diaryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_view, parent, false);
        diaryViewHolder diaryHolder = new diaryViewHolder(diaryView, diaryListener);

        return  diaryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull diaryViewHolder holder, int position) {
        holder.diaryDateText.setText(dDates.get(position));
    }

    @Override
    public int getItemCount() {
        return dDates.size();
    }

    public void newData(ArrayList dates)
    {
        dDates = dates;
    }

    public interface diaryListener
    {
        void diaryOnClick(int position);
    }
}
