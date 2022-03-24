package com.example.notesforbots.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesforbots.App;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_KEY = "NOTE_ENTITY_KEY";
    EditText titleEditText;
    EditText notesTextEditText;
    Button saveButton;
    Button deleteButton;
    NotesRepo repo;
    NotesEntity notesEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.note_activity);
        repo = App.get(this).getNotesRepo();
        initViews();
        fillNote();
    }

    private void fillNote() {
        notesEntity = getIntent().getParcelableExtra(EXTRA_KEY);
        titleEditText.setText(notesEntity.getNotesTitle());
        notesTextEditText.setText(notesEntity.getNotesText());
    }

    private void initViews() {
        titleEditText = findViewById(R.id.note_activity__title_edit_text);
        notesTextEditText = findViewById(R.id.note_activity__notes_text_edit_text);
        saveButton = findViewById(R.id.note_activity__save_button);
        deleteButton = findViewById(R.id.note_activity__delete_button);
        saveButton.setOnClickListener(v -> {
            repo.editNotes(getEditedNote());
            setResult(RESULT_OK);
            finish();
        });
        deleteButton.setOnClickListener(v->{
            repo.deleteNote(notesEntity);
            setResult(RESULT_OK);
            finish();
        });
    }

    private NotesEntity getEditedNote() {
        notesEntity.setNotesText(notesTextEditText.getText().toString());
        notesEntity.setNotesTitle(titleEditText.getText().toString());
        return notesEntity;
    }
}
