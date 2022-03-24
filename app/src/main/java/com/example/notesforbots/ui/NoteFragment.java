package com.example.notesforbots.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesforbots.App;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

public class NoteFragment extends Fragment {
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
    private Controller controller;

    public interface Controller {
        void onDeleteButtonClick();

        void onSaveButtonClick();
    }

    public NoteFragment(NotesEntity notesEntity) {
        this.notesEntity = notesEntity;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NoteFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repo = App.get().localNotesRepo;
        initViews(view);
        fillNote();
    }

    private void fillNote() {
        titleEditText.setText(notesEntity.getNotesTitle());
        notesTextEditText.setText(notesEntity.getNotesText());
    }

    private void initViews(View view) {
        titleEditText = view.findViewById(R.id.note_activity__title_edit_text);
        notesTextEditText = view.findViewById(R.id.note_activity__notes_text_edit_text);
        saveButton = view.findViewById(R.id.note_activity__save_button);
        deleteButton = view.findViewById(R.id.note_activity__delete_button);
        blackColorImageView = view.findViewById(R.id.black_color_image_view);
        blueColorImageView = view.findViewById(R.id.blue_color_image_view);
        whiteColorImageView = view.findViewById(R.id.white_color_image_view);
        greenColorImageView = view.findViewById(R.id.green_color_image_view);
        orangeColorImageView = view.findViewById(R.id.orange_color_image_view);
        setClickListeners();
    }

    private void setClickListeners() {
        saveButton.setOnClickListener(v -> {
            repo.editNotes(getEditedNote());
            controller.onSaveButtonClick();
        });
        deleteButton.setOnClickListener(v -> {
            repo.deleteNote(notesEntity);
            controller.onDeleteButtonClick();
        });
        blackColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF000000")));
        blueColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF3700B3")));
        whiteColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FFFFFFFF")));
        greenColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF018786")));
        orangeColorImageView.setOnClickListener(v -> notesEntity.setNotesColor(Color.parseColor("#FF9800")));
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
