package com.example.notesforbots.ui.adapter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.App;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.example.notesforbots.ui.AlertDialogFragment;
import com.example.notesforbots.ui.adapter.NotesAdapter;

public class SwipeCallback extends ItemTouchHelper.Callback {

    private final NotesAdapter adapter;
    NotesRepo notesRepo;
    FragmentActivity activity;


    public SwipeCallback(NotesAdapter adapter, FragmentActivity activity) {
        this.activity = activity;
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
        AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance(notesEntity);
        alertDialogFragment.show(activity.getSupportFragmentManager(), "deleteDialogue");
//        adapter.deleteItem(notesEntity.getId());
//        notesRepo.deleteNote(notesEntity);
    }
}
