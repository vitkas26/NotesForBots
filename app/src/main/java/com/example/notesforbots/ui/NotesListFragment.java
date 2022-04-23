package com.example.notesforbots.ui;

import android.content.Context;
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
import com.example.notesforbots.ui.adapter.NotesAdapter;
import com.example.notesforbots.ui.adapter.SwipeCallback;

public class NotesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button newNoteButton;
    private NotesAdapter notesAdapter;
    private NotesRepo notesRepo;
    private Controller controller;

    public interface Controller {
        void createNewNote();
        void showNote(NotesEntity notesEntity);
    }

    public NotesListFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller){
            controller = (Controller) context;
        }else{
            throw new IllegalStateException("Activity must implement NotesListFragment.Controller");
        }
    }

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
           controller.createNewNote();
        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.notes_list_fragment__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnClickListener(new NotesAdapter.OnNoteClickListener(){
            @Override
            public void onClickItem(NotesEntity notesEntity) {
                controller.showNote(notesEntity);
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

    public void onNoteCreated(NotesEntity notesEntity){
        notesRepo.addNote(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }

    public void onDeleteNote(NotesEntity notesEntity){
        notesRepo.deleteNote(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }

    public void onNoteEdited(NotesEntity notesEntity){
        notesRepo.editNotes(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }
}
