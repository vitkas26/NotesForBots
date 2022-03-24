package com.example.notesforbots.domain;

import java.util.List;

public interface NotesRepo {
    List<NotesEntity> getNotes();
    void addNote(NotesEntity notesEntity);
    void editNotes(NotesEntity notesEntity);
    void deleteNote(NotesEntity notesEntity);
    void swapNote(NotesEntity notesEntity);
}
