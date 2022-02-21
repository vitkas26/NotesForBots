package com.example.notesforbots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    List<NotesEntity> data = new ArrayList<>();

    public void setData(List<NotesEntity> notesEntities) {
        data = notesEntities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NotesViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bind(getItemFromData(position));
    }

    public NotesEntity getItemFromData(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
