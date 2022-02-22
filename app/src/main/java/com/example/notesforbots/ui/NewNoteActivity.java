package com.example.notesforbots.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesforbots.App;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.example.notesforbots.R;

public class NewNoteActivity extends AppCompatActivity {
    EditText titleEditText;
    EditText notesTextEditText;
    Button saveButton;
    Button cancelButton;
    NotesRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        initViews();
        repo = App.get(this).getNotesRepo();
    }

    private void initViews() {
        titleEditText = findViewById(R.id.title_edit_text);
        notesTextEditText = findViewById(R.id.notes_text_edit_text);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        saveButton.setOnClickListener(v -> {
            repo.addNotes(new NotesEntity(String.valueOf(titleEditText.getText()), String.valueOf(notesTextEditText.getText())));
            setResult(RESULT_OK);
            finish();
        });
    }
}
