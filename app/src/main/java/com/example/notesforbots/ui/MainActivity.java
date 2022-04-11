package com.example.notesforbots.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;

public class MainActivity extends AppCompatActivity implements
        NotesListFragment.Controller,
        NewNoteFragment.Controller,
        NoteFragment.Controller {

    private final static String TAG_NOTE_LIST_FRAGMENT = "TAG_NOTE_LIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment notesListFragment = new NotesListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__first_fragment_container, notesListFragment, TAG_NOTE_LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void createNewNote() {
        Fragment newNoteFragment = new NewNoteFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, newNoteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showNote(NotesEntity notesEntity) {
        NoteFragment noteFragment = NoteFragment.newInstance(notesEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__second_fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();
    }

    //get action from note fragment when save button clicked
    @Override
    public void onSaveButtonClick(NotesEntity notesEntity) {
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_NOTE_LIST_FRAGMENT);
        notesListFragment.onNoteCreated(notesEntity);
        getSupportFragmentManager().popBackStack();
    }

    //get action from note fragment when delete button clicked
    @Override
    public void onDeleteButtonClick(NotesEntity notesEntity) {
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_NOTE_LIST_FRAGMENT);
        AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance(notesEntity);
        alertDialogFragment.show(this.getSupportFragmentManager(), "deleteDialogue");
        getSupportFragmentManager().popBackStack();
    }

    //get action from new note fragment when cancel button clicked
    @Override
    public void onCancelButtonClick() {
        Toast.makeText(this, "Создание заметки было отклонено", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().popBackStack();
    }

    //get action from new note fragment when save button clicked
    @Override
    public void onSaveNoteNoteFragment(NotesEntity notesEntity) {
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_NOTE_LIST_FRAGMENT);
        notesListFragment.onNoteEdited(notesEntity);
        getSupportFragmentManager().popBackStack();
    }
}