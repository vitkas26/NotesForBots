package com.example.notesforbots;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LocalNotesRepoImpl implements NotesRepo{
    ArrayList<NotesEntity> localNotesRepo = new ArrayList<>();

    public LocalNotesRepoImpl(){
        localNotesRepo.addAll(createNotes());
    }

    private ArrayList<NotesEntity> createNotes() {
        ArrayList<NotesEntity> notesEntities = new ArrayList<>();
        notesEntities.add(new NotesEntity("Header","Your Text Here !"));
        return notesEntities;
    }

    @Override
    public List<NotesEntity> getNotes() {
        return localNotesRepo;
    }

    @Override
    public void addNotes(NotesEntity notesEntity) {
    localNotesRepo.add(notesEntity);
    }
}
