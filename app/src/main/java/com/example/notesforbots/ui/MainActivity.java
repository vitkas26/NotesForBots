package com.example.notesforbots.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.notesforbots.App;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.example.notesforbots.R;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_NOTE_REQUEST_CODE = 777;
    private static final int NOTE_ACTIVITY_REQUEST_CODE = 888;
    RecyclerView recyclerView;
    Button newNoteButton;
    NotesAdapter notesAdapter;
    NotesRepo notesRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesRepo = App.get().localNotesRepo;
        initRecyclerView();
        initButton();
    }

    private void initButton() {
        newNoteButton = findViewById(R.id.new_note_button);
        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewNoteActivity.class);
            startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnClickListener(new NotesAdapter.OnNoteClickListener(){
            @Override
            public void onClickItem(NotesEntity notesEntity) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                intent.putExtra(NoteActivity.EXTRA_KEY, notesEntity);
                startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
            }

            @Override
            public void onDeleteItem(NotesEntity notesEntity) {
                notesRepo.deleteNote(notesEntity);
                notesAdapter.deleteItem(notesEntity.getId());
            }

            @Override
            public void onRefreshItem(NotesEntity notesEntity) {
                notesRepo.swapNote(notesEntity);
                notesAdapter.setData(notesRepo.getNotes());
            }
        });
        notesAdapter.setData(notesRepo.getNotes());
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_REQUEST_CODE || requestCode == NOTE_ACTIVITY_REQUEST_CODE
                && resultCode == RESULT_OK) {
            notesAdapter.setData(notesRepo.getNotes());
        } else {
            throw new IllegalArgumentException();
        }
    }
}