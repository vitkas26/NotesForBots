package com.example.notesforbots;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NotesViewHolder extends RecyclerView.ViewHolder {
    private final TextView notesHeaderTextView = itemView.findViewById(R.id.notes_header_text_view);
    private final TextView notesTextTextView = itemView.findViewById(R.id.notes_text_text_view);
    private final TextView notesDateTextView = itemView.findViewById(R.id.notes_date_text_view);

    public NotesViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        super(inflater.inflate(R.layout.item_note, parent, false));
    }

    public void bind(NotesEntity notesEntity) {
        notesHeaderTextView.setText(notesEntity.getNotesHeader());
        notesTextTextView.setText(notesEntity.getNotesText());
        notesDateTextView.setText(notesEntity.getNotesCreationDate());
    }
}
