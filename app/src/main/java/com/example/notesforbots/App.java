package com.example.notesforbots;

import android.app.Application;

import com.example.notesforbots.data.SnappyDBNotesRepoImpl;
import com.example.notesforbots.domain.NotesRepo;

public class App extends Application {
    private static App sInstance = null;
//    public final NotesRepo localNotesRepo = new LocalNotesRepoImpl();
    public NotesRepo localNotesRepo;
    SnappyDBNotesRepoImpl snappyRepo = new SnappyDBNotesRepoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        localNotesRepo = new SharedPreferenceNotesRepoImpl(this);
        snappyRepo.initDB(this);
        localNotesRepo = snappyRepo;
    }

    public static App get(){
        return sInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        snappyRepo.destroyDB();
    }
}
