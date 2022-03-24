package com.example.notesforbots.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.R;


public class NotesViewHolder extends RecyclerView.ViewHolder {
    private final TextView notesTitleTextView = itemView.findViewById(R.id.item_note__title_text_view);
    private final TextView notesTextTextView = itemView.findViewById(R.id.item_note__text_text_view);
    private final TextView notesDateTextView = itemView.findViewById(R.id.item_note__date_text_view);
    private final CardView rootView = itemView.findViewById(R.id.item_note__card_view);
    private final ImageView deleteButton = itemView.findViewById(R.id.item_note__delete_button);
    private final ImageView refreshButton = itemView.findViewById(R.id.item_note__refresh_button);
    NotesAdapter.OnNoteClickListener onNoteClickListener;

    public NotesViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater, NotesAdapter.OnNoteClickListener onNoteClickListener) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        this.onNoteClickListener = onNoteClickListener;
    }

    public void bind(NotesEntity notesEntity) {
        notesTitleTextView.setText(notesEntity.getNotesTitle());
        notesTextTextView.setText(notesEntity.getNotesText());
        notesDateTextView.setText(notesEntity.getNotesCreationDate());
        notesTitleTextView.setTextColor(notesEntity.getTitleColor());
        notesTextTextView.setTextColor(notesEntity.getTextColor());
        notesDateTextView.setTextColor(notesEntity.getDateColor());
        rootView.setCardBackgroundColor(notesEntity.getNotesColor());
        itemView.setOnClickListener(v -> onNoteClickListener.onClickItem(notesEntity));
        deleteButton.setOnClickListener(view -> onNoteClickListener.onDeleteItem(notesEntity));
        refreshButton.setOnClickListener(view -> onNoteClickListener.onRefreshItem(notesEntity));
        if (notesEntity.getNotesColor() == Color.parseColor("#FFFFFFFF")) {
            refreshButton.setImageDrawable(ResourcesCompat.getDrawable(rootView.getResources(), R.drawable.ic_baseline_refresh_24_black, null));
            deleteButton.setImageDrawable(ResourcesCompat.getDrawable(rootView.getResources(), R.drawable.ic_baseline_delete_24_black, null));
        } else {
            refreshButton.setImageDrawable(ResourcesCompat.getDrawable(rootView.getResources(), R.drawable.ic_baseline_refresh_24, null));
            deleteButton.setImageDrawable(ResourcesCompat.getDrawable(rootView.getResources(), R.drawable.ic_baseline_delete_24, null));

        }
    }
}
