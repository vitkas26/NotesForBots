package com.example.notesforbots.data;

import android.content.Context;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.Collections;

public class SnappyDBNotesRepoImpl implements NotesRepo {

    private static final String SNAPPY_DB_ARRAY = "array_list";
    private DB db;

    public void initDB(Context context) {
        try {
            db = DBFactory.open(context);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void destroyDB() {
        try {
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        db = null;
    }

    @Override
    public ArrayList<NotesEntity> getNotes() {

        final ArrayList<NotesEntity> data = new ArrayList<>();
        try {
            for (String[] batch : db.allKeysIterator().byBatch(50)) {
                for (String key : batch) {
                    data.add(db.getObject(key, NotesEntity.class));
                }
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void addNote(NotesEntity notesEntity) {
        try {
            db.put(notesEntity.getId(), notesEntity);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editNotes(NotesEntity notesEntity) {
        NotesEntity notesEntityOld;
        try {
            notesEntityOld = db.getObject(notesEntity.getId(), NotesEntity.class);
            deleteNote(notesEntityOld);
            db.put(notesEntity.getId(), notesEntity);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNote(NotesEntity notesEntity) {
        try {
            db.del(notesEntity.getId());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public int findPosition(NotesEntity notesEntity) {
        ArrayList<NotesEntity> data = getNotes();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(notesEntity.getId())) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No elements in a repo");
    }

    @Override
    public void swapNote(NotesEntity notesEntity) {
//        final ArrayList<NotesEntity> data = getNotes();
        String noteEntityId = notesEntity.getId();
//        try {
//            Collections.swap(data, findPosition(notesEntity), findPosition(notesEntity) - 1);
//        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
//            System.out.println("Array out of bound");
//        }
        try {
            for (String[] batch : db.allKeysIterator().byBatch(50)) {
                for (int i = 0; i < batch.length; i++) {
                    if(batch[i].equals(noteEntityId)){
                        String idOfNoteAbove = batch[i-1];
                        String idOfNote = batch[i];
                        NotesEntity noteAbove = db.getObject(idOfNoteAbove, NotesEntity.class);
                        NotesEntity note = db.getObject(idOfNote, NotesEntity.class);
                        deleteNote(noteAbove);
                        deleteNote(note);
                        note.setId(idOfNoteAbove);
                        db.put(note.getId(), note);
                        noteAbove.setId(idOfNote);
                        db.put(noteAbove.getId(), noteAbove);
                    }
                }
            }
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
