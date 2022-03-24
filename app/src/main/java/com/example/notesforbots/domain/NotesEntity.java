package com.example.notesforbots.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NotesEntity implements Parcelable {
    private String id = String.valueOf(new Random().nextInt(1000 + 1));
    private String notesTitle;
    private String notesText;
    private String notesCreationDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US).
            format(Calendar.getInstance().getTime());

    public NotesEntity(String notesTitle, String notesText) {
        this.notesTitle = notesTitle;
        this.notesText = notesText;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public void setNotesText(String notesText) {
        this.notesText = notesText;
    }

    protected NotesEntity(Parcel in) {
        id = in.readString();
        notesTitle = in.readString();
        notesText = in.readString();
        notesCreationDate = in.readString();
    }

    public static final Creator<NotesEntity> CREATOR = new Creator<NotesEntity>() {
        @Override
        public NotesEntity createFromParcel(Parcel in) {
            return new NotesEntity(in);
        }

        @Override
        public NotesEntity[] newArray(int size) {
            return new NotesEntity[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public String getNotesText() {
        return notesText;
    }

    public String getNotesCreationDate() {
        return notesCreationDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(notesTitle);
        parcel.writeString(notesText);
        parcel.writeString(notesCreationDate);
    }
}
