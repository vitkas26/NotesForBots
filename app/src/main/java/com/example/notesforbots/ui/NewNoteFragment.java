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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;

public class NewNoteFragment extends Fragment {
    private EditText titleEditText;
    private EditText notesTextEditText;
    private Button saveButton;
    private Button cancelButton;
    private ImageView blackColorImageView;
    private ImageView blueColorImageView;
    private ImageView whiteColorImageView;
    private ImageView greenColorImageView;
    private ImageView orangeColorImageView;
    private NotesEntity notesEntity;
    private Controller controller;

    public interface Controller {
        void onCancelButtonClick();
        void onSaveButtonClick(NotesEntity notesEntity);

    }

    public NewNoteFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NewNoteFragment.Controller){
            controller = (Controller) context;
        }else {
            throw new IllegalStateException("Activity must implement NewNoteFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        notesEntity = new NotesEntity("", "");
    }

    private void initViews(View view) {
        titleEditText = view.findViewById(R.id.title_edit_text);
        notesTextEditText = view.findViewById(R.id.notes_text_edit_text);
        saveButton = view.findViewById(R.id.save_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        blackColorImageView = view.findViewById(R.id.black_color_image_view);
        blueColorImageView = view.findViewById(R.id.blue_color_image_view);
        whiteColorImageView = view.findViewById(R.id.white_color_image_view);
        greenColorImageView = view.findViewById(R.id.green_color_image_view);
        orangeColorImageView = view.findViewById(R.id.orange_color_image_view);
        setClickListeners();
    }

    private void setClickListeners() {
        cancelButton.setOnClickListener(view -> {
        controller.onCancelButtonClick();
        });
        saveButton.setOnClickListener(v -> {
            notesEntity.setNotesTitle(String.valueOf(titleEditText.getText()));
            notesEntity.setNotesText(String.valueOf(notesTextEditText.getText()));
            setColor(notesEntity);
            controller.onSaveButtonClick(notesEntity);
            Toast.makeText(getContext(), "Заметка успешно сохранена", Toast.LENGTH_SHORT).show();
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
