package com.example.notesforbots;

import android.app.Application;
import android.content.Context;

import com.example.notesforbots.data.LocalNotesRepoImpl;
import com.example.notesforbots.domain.NotesRepo;

public class App extends Application {
    private static App sInstance = null;
    public final NotesRepo localNotesRepo = new LocalNotesRepoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App get(){
        return sInstance;
    }
}
