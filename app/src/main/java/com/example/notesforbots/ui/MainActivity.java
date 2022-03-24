package com.example.notesforbots.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;

public class MainActivity extends AppCompatActivity implements
        NotesListFragment.Controller,
        NewNoteFragment.Controller,
        NoteFragment.Controller{

    private final static String TAG_NOTE_LIST_FRAGMENT = "TAG_NOTE_LIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment notesListFragment = new NotesListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__fragment_container, notesListFragment, TAG_NOTE_LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void createNewNote() {
        Fragment newNoteFragment = new NewNoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, newNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showNote(NotesEntity notesEntity) {
        NoteFragment noteFragment = new NoteFragment(notesEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCancelButtonClick() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onSaveButtonClick(NotesEntity notesEntity) {
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_NOTE_LIST_FRAGMENT);
        notesListFragment.onNoteCreated(notesEntity);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onDeleteButtonClick() {

    }

    @Override
    public void onSaveButtonClick() {

    }
}