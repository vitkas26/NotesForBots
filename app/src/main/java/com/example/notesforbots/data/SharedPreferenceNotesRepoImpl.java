package com.example.notesforbots.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class SharedPreferenceNotesRepoImpl implements NotesRepo {

    private static final String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_REPO";
    private static final String SHARED_PREFERENCES_NOTES_ENTITY = "SHARED_PREFERENCES_NOTES_ENTITY";
    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public SharedPreferenceNotesRepoImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public ArrayList<NotesEntity> getNotes() {
        final String notesEntitiesJson = sharedPreferences.getString(SHARED_PREFERENCES_NOTES_ENTITY, "");
        if (!notesEntitiesJson.equals("")) {
            Type type = new TypeToken<ArrayList<NotesEntity>>() {
            }.getType();
            return gson.fromJson(notesEntitiesJson, type);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void addNote(NotesEntity notesEntity) {
        final ArrayList<NotesEntity> data = getNotes();
        data.add(notesEntity);
        final String jsonString = gson.toJson(data);
        sharedPreferences
                .edit()
                .putString(SHARED_PREFERENCES_NOTES_ENTITY, jsonString)
                .apply();
    }

    @Override
    public void editNotes(NotesEntity notesEntity) {
        final ArrayList<NotesEntity> data = getNotes();
        data.set(findPosition(notesEntity), notesEntity);
        final String jsonString = gson.toJson(data);
        sharedPreferences
                .edit()
                .putString(SHARED_PREFERENCES_NOTES_ENTITY, jsonString)
                .apply();
    }

    @Override
    public void deleteNote(NotesEntity notesEntity) {
        final ArrayList<NotesEntity> data = getNotes();
        data.remove(findPosition(notesEntity));
        final String jsonString = gson.toJson(data);
        sharedPreferences
                .edit()
                .putString(SHARED_PREFERENCES_NOTES_ENTITY, jsonString)
                .apply();
    }

    public int findPosition(NotesEntity notesEntity) {
        final ArrayList<NotesEntity> data = getNotes();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(notesEntity.getId())) {
                return i;
            }
        }
        throw new ArrayIndexOutOfBoundsException("No elements in a repo");
    }

    @Override
    public void swapNote(NotesEntity notesEntity) {
        final ArrayList<NotesEntity> data = getNotes();
        try {
            Collections.swap(data, findPosition(notesEntity), findPosition(notesEntity) - 1);
            final String jsonString = gson.toJson(data);
            sharedPreferences
                    .edit()
                    .putString(SHARED_PREFERENCES_NOTES_ENTITY, jsonString)
                    .apply();
        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            System.out.println("Array out of bound");
        }
    }
}
