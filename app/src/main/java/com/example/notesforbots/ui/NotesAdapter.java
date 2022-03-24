package com.example.notesforbots.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.domain.NotesEntity;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    private List<NotesEntity> data = new ArrayList<>();
    OnNoteClickListener onNoteClickListener;

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

    public void setData(List<NotesEntity> notesEntities) {
        data.clear();
        data.addAll(notesEntities);
        notifyDataSetChanged();
    }

    public void deleteItem(String itemId) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(itemId)) {
                data.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    public void setOnClickListener(NotesAdapter.OnNoteClickListener onClickListener) {
        this.onNoteClickListener = onClickListener;
    }

    public interface OnNoteClickListener {
        void onClickItem(NotesEntity notesEntity);

        void onDeleteItem(NotesEntity notesEntity);

        void onRefreshItem(NotesEntity notesEntity);

    }
}
