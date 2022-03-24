package com.example.notesforbots.data;

import android.util.Log;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

import java.util.ArrayList;
import java.util.List;

public class LocalNotesRepoImpl implements NotesRepo {
    ArrayList<NotesEntity> localNotesRepo = new ArrayList<>();

    public LocalNotesRepoImpl() {
        localNotesRepo.addAll(createNotes());
    }

    private ArrayList<NotesEntity> createNotes() {
        ArrayList<NotesEntity> notesEntities = new ArrayList<>();
        notesEntities.add(new NotesEntity("Header", "Your Text Here !"));
        return notesEntities;
    }

    private int findPosition(NotesEntity notesEntity) {
        for (int i = 0; i < localNotesRepo.size(); i++) {
            if (localNotesRepo.get(i).getId().equals(notesEntity.getId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<NotesEntity> getNotes() {
        return localNotesRepo;
    }

    @Override
    public void addNotes(NotesEntity notesEntity) {
        localNotesRepo.add(notesEntity);
    }

    @Override
    public void editNotes(NotesEntity notesEntity) {
        localNotesRepo.set(findPosition(notesEntity), notesEntity);
    }

    @Override
    public void deleteNote(NotesEntity notesEntity) {
        localNotesRepo.remove(findPosition(notesEntity));
    }

}
