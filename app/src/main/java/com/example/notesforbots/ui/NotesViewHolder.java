package com.example.notesforbots.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.OnNoteClickListener;


public class NotesViewHolder extends RecyclerView.ViewHolder {
    OnNoteClickListener onNoteClickListener;
    private final TextView notesHeaderTextView = itemView.findViewById(R.id.notes_header_text_view);
    private final TextView notesTextTextView = itemView.findViewById(R.id.notes_text_text_view);
    private final TextView notesDateTextView = itemView.findViewById(R.id.notes_date_text_view);

    public NotesViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater, OnNoteClickListener onNoteClickListener) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        this.onNoteClickListener = onNoteClickListener;
    }

    public void bind(NotesEntity notesEntity) {
        notesHeaderTextView.setText(notesEntity.getNotesTitle());
        notesTextTextView.setText(notesEntity.getNotesText());
        notesDateTextView.setText(notesEntity.getNotesCreationDate());
        itemView.setOnClickListener(v->onNoteClickListener.onClickListener(notesEntity));
    }
}
