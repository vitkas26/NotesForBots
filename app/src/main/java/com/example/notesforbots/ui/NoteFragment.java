package com.example.notesforbots.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;

public class NoteFragment extends Fragment {
    private static final String NOTES_ENTITY_KEY = "NOTES_ENTITY_KEY";
    private EditText titleEditText;
    private EditText notesTextEditText;
    private Button saveButton;
    private Button deleteButton;
    private ImageView blackColorImageView;
    private ImageView blueColorImageView;
    private ImageView whiteColorImageView;
    private ImageView greenColorImageView;
    private ImageView orangeColorImageView;
    private NotesEntity notesEntity;
    private Controller controller;

    public interface Controller {
        void onDeleteButtonClick(NotesEntity notesEntity);
        void onSaveNoteNoteFragment(NotesEntity notesEntity);
    }

    public static NoteFragment newInstance (NotesEntity notesEntity) {
        NoteFragment noteFragment = new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NOTES_ENTITY_KEY, notesEntity);
        noteFragment.setArguments(bundle);
        return noteFragment;
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

        initToolbar();
        notesEntity = getArguments().getParcelable(NOTES_ENTITY_KEY);
        initViews(view);
        fillNote();
    }

    private void initToolbar() {
        final Toolbar toolbar = getView().findViewById(R.id.notes_list_fragment__toolbar);
        toolbar.setTitle("Редактирование заметки");
        initMenu(toolbar);
    }

    private void initMenu(Toolbar toolbar){
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.note_menu, menu);
        menu.findItem(R.id.note_menu__menu_save).setOnMenuItemClickListener(v->{
            getEditedNote();
            controller.onSaveNoteNoteFragment(notesEntity);
            return true;
        });
        menu.findItem(R.id.note_menu__menu_delete).setOnMenuItemClickListener(v->{
            controller.onDeleteButtonClick(notesEntity);
            return true;
        });
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
            notesEntity = getEditedNote();
            controller.onSaveNoteNoteFragment(notesEntity);
            Toast.makeText(getContext(), "Заметка успешно сохранена", Toast.LENGTH_SHORT).show();
        });
        deleteButton.setOnClickListener(v -> {
            controller.onDeleteButtonClick(notesEntity);
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
