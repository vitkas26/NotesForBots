package com.example.notesforbots.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.App;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

public class NotesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button newNoteButton;
    private NotesAdapter notesAdapter;
    private NotesRepo notesRepo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        notesRepo = App.get().localNotesRepo;
        initRecyclerView(view);
        initButton(view);
        initSwapAction();
    }

    private void initButton(View view) {
        newNoteButton = view.findViewById(R.id.notes_list_fragment__new_note_button);
        newNoteButton.setOnClickListener(v -> {
//            Intent intent = new Intent(this, NewNoteActivity.class);
//            startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.notes_list_fragment__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnClickListener(new NotesAdapter.OnNoteClickListener(){
            @Override
            public void onClickItem(NotesEntity notesEntity) {
//                Intent intent = new Intent(notes_list_fragment.this, NoteActivity.class);
//                intent.putExtra(NoteActivity.EXTRA_KEY, notesEntity);
//                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
            }

            @Override
            public void onDeleteItem(NotesEntity notesEntity) {
                notesRepo.deleteNote(notesEntity);
                notesAdapter.deleteItem(notesEntity.getId());
            }

            @Override
            public void onRefreshItem(NotesEntity notesEntity) {
                notesRepo.swapNote(notesEntity);
                notesAdapter.setData(notesRepo.getNotes());
            }

        });
        notesAdapter.setData(notesRepo.getNotes());
        recyclerView.setAdapter(notesAdapter);
    }

    private void initSwapAction() {
        ItemTouchHelper.Callback callback = new SwipeCallback(notesAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }
}
