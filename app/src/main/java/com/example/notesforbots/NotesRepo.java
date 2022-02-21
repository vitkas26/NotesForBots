package com.example.notesforbots;

import java.util.List;

public interface NotesRepo {
    List<NotesEntity> getNotes();
    void addNotes(NotesEntity notesEntity);
}
