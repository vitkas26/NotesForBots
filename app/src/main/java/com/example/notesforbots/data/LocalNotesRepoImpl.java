package com.example.notesforbots.data;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<NotesEntity> getNotes() {
        return localNotesRepo;
    }

    @Override
    public void addNote(NotesEntity notesEntity) {
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

    public int findPosition(NotesEntity notesEntity) {
        for (int i = 0; i < localNotesRepo.size(); i++) {
            if (localNotesRepo.get(i).getId().equals(notesEntity.getId())) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No elements in a repo");
    }

    @Override
    public void swapNote(NotesEntity notesEntity) {
        try {
            Collections.swap(localNotesRepo, findPosition(notesEntity), findPosition(notesEntity) - 1);
        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            System.out.println("Array out of bound");
        }
    }
}
