package com.example.notesforbots;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_NOTE_REQUEST_CODE = 777;
    private static final String TAG = "@@@";
    RecyclerView recyclerView;
    Button newNoteButton;
    NotesAdapter notesAdapter;
    NotesRepo notesRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesRepo = App.get(this).getNotesRepo();
        initRecyclerView();
        initButton();
    }

    private void initButton() {
        newNoteButton = findViewById(R.id.new_note_button);
        newNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewNote.class);
            startActivityForResult(intent, NEW_NOTE_REQUEST_CODE);
        });
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter();
        notesAdapter.setData(notesRepo.getNotes());
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            notesAdapter.setData(notesRepo.getNotes());
        } else {
           throw new IllegalArgumentException();
        }
    }
}