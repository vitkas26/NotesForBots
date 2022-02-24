package com.example.notesforbots.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.OnNoteClickListener;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    List<NotesEntity> data = new ArrayList<>();
    OnNoteClickListener onNoteClickListener;

    public void setData(List<NotesEntity> notesEntities) {
        data.clear();
        data.addAll(notesEntities);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnNoteClickListener onClickListener){
        this.onNoteClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NotesViewHolder(parent, inflater, onNoteClickListener);
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
