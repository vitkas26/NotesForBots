package com.example.notesforbots.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
    ImageView blackColorImageView;
    ImageView blueColorImageView;
    ImageView whiteColorImageView;
    ImageView greenColorImageView;
    ImageView orangeColorImageView;
    NotesEntity notesEntity;
    NotesRepo repo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        initViews();
        repo = App.get().localNotesRepo;
        notesEntity = new NotesEntity("", "");
    }

    private void initViews() {
        titleEditText = findViewById(R.id.title_edit_text);
        notesTextEditText = findViewById(R.id.notes_text_edit_text);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);
        blackColorImageView = findViewById(R.id.black_color_image_view);
        blueColorImageView = findViewById(R.id.blue_color_image_view);
        whiteColorImageView = findViewById(R.id.white_color_image_view);
        greenColorImageView = findViewById(R.id.green_color_image_view);
        orangeColorImageView = findViewById(R.id.orange_color_image_view);
        setClickListeners();
    }

    private void setClickListeners() {
        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
        saveButton.setOnClickListener(v -> {
            notesEntity.setNotesTitle(String.valueOf(titleEditText.getText()));
            notesEntity.setNotesText(String.valueOf(notesTextEditText.getText()));
            setColor(notesEntity);
            repo.addNotes(notesEntity);
            setResult(RESULT_OK);
            finish();
        });
        blackColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF000000")));
        blueColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF3700B3")));
        whiteColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FFFFFFFF")));
        greenColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF018786")));
        orangeColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF9800")));
    }

    private void setColor(NotesEntity notesEntity) {
        if (notesEntity.getNotesColor() != Color.parseColor("#FF03DAC5")) {
            notesEntity.setTitleColor(Color.parseColor("#FFFFFFFF"));
            notesEntity.setTextColor(Color.parseColor("#FFFFFFFF"));
            notesEntity.setDateColor(Color.parseColor("#FFFFFFFF"));
        }
        if (notesEntity.getNotesColor() == Color.parseColor("#FFFFFFFF")) {
            notesEntity.setTitleColor(Color.parseColor("#FF000000"));
            notesEntity.setTextColor(Color.parseColor("#FF000000"));
            notesEntity.setDateColor(Color.parseColor("#FF000000"));
        }
    }
}
