package com.example.notesforbots.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesforbots.App;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_KEY = "NOTE_ENTITY_KEY";
    private EditText titleEditText;
    private EditText notesTextEditText;
    private Button saveButton;
    private Button deleteButton;
    private ImageView blackColorImageView;
    private ImageView blueColorImageView;
    private ImageView whiteColorImageView;
    private ImageView greenColorImageView;
    private ImageView orangeColorImageView;
    private NotesRepo repo;
    private NotesEntity notesEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.note_fragment);
        repo = App.get().localNotesRepo;
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
        blackColorImageView = findViewById(R.id.black_color_image_view);
        blueColorImageView = findViewById(R.id.blue_color_image_view);
        whiteColorImageView = findViewById(R.id.white_color_image_view);
        greenColorImageView = findViewById(R.id.green_color_image_view);
        orangeColorImageView = findViewById(R.id.orange_color_image_view);
        setClickListeners();
    }

    private void setClickListeners() {
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
        blackColorImageView.setOnClickListener(v-> notesEntity.setNotesColor(Color.parseColor("#FF000000")));
        blueColorImageView.setOnClickListener(v-> notesEntity.setNotesColor(Color.parseColor("#FF3700B3")));
        whiteColorImageView.setOnClickListener(v-> notesEntity.setNotesColor(Color.parseColor("#FFFFFFFF")));
        greenColorImageView.setOnClickListener(v-> notesEntity.setNotesColor(Color.parseColor("#FF018786")));
        orangeColorImageView.setOnClickListener(v-> notesEntity.setNotesColor(Color.parseColor("#FF9800")));
    }

    private NotesEntity getEditedNote() {
        notesEntity.setNotesText(notesTextEditText.getText().toString());
        notesEntity.setNotesTitle(titleEditText.getText().toString());
        setColor(notesEntity);
        return notesEntity;
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
