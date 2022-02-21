package com.example.notesforbots;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewNote extends AppCompatActivity {
    String TAG = "@@@";
    EditText titleEditText;
    EditText notesTextEditText;
    Button saveButton;
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
        saveButton.setOnClickListener(v -> {
            repo.addNotes(new NotesEntity(String.valueOf(titleEditText.getText()), String.valueOf(notesTextEditText.getText())));
            setResult(RESULT_OK);
            finish();
        });
    }
}
