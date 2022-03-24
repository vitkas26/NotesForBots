package com.example.notesforbots.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.App;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.example.notesforbots.ui.adapter.NotesAdapter;

public class SwipeCallback extends ItemTouchHelper.Callback {

    private final NotesAdapter adapter;
    NotesRepo notesRepo;


    public SwipeCallback(NotesAdapter adapter) {
        this.adapter = adapter;
        notesRepo =App.get().localNotesRepo;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        NotesEntity notesEntity = adapter.getItemFromData(viewHolder.getAdapterPosition());
        adapter.deleteItem(notesEntity.getId());
        notesRepo.deleteNote(notesEntity);
    }
}
