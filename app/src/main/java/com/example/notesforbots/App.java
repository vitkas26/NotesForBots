package com.example.notesforbots;

import android.app.Application;
import android.content.Context;

import com.example.notesforbots.data.LocalNotesRepoImpl;
import com.example.notesforbots.domain.NotesRepo;

public class App extends Application {
    private final NotesRepo localNotesRepo = new LocalNotesRepoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public NotesRepo getNotesRepo(){
        return localNotesRepo;
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }
}
