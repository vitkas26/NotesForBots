package com.example.notesforbots;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NotesEntity implements Parcelable {
    ArrayList<NotesEntity> notesEntities = new ArrayList<>();

    private final String id = String.valueOf(new Random().nextInt(1000 + 1));
    private final String notesHeader;
    private final String notesText;
    private final String notesCreationDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US).
            format(Calendar.getInstance().getTime());

    public NotesEntity(String notesHeader, String notesText) {
        this.notesHeader = notesHeader;
        this.notesText = notesText;
    }

    protected NotesEntity(Parcel in) {
        notesEntities = in.createTypedArrayList(NotesEntity.CREATOR);
        notesHeader = in.readString();
        notesText = in.readString();
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

    public String getNotesHeader() {
        return notesHeader;
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
        parcel.writeTypedList(notesEntities);
        parcel.writeString(id);
        parcel.writeString(notesHeader);
        parcel.writeString(notesText);
    }
}
