package com.example.notesforbots.domain;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NotesEntity implements Parcelable {
    private String id = String.valueOf(new Random().nextInt(1000 + 1));
    private String notesTitle;
    private String notesText;
    private int notesColor;
    private int textColor;
    private int titleColor;
    private int dateColor;

    private String notesCreationDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.US).
            format(Calendar.getInstance().getTime());

    public NotesEntity(String notesTitle, String notesText) {
        this.notesTitle = notesTitle;
        this.notesText = notesText;
        notesColor = Color.parseColor("#FF03DAC5");
        textColor = Color.parseColor("#3F51B5");
        titleColor = Color.parseColor("#FFFFFFFF");
        dateColor = Color.parseColor("#FFFFFFFF");
    }

    public int getDateColor() {
        return dateColor;
    }

    public void setDateColor(int dateColor) {
        this.dateColor = dateColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getNotesColor() {
        return notesColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setNotesColor(int notesColor) {
        this.notesColor = notesColor;
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
        notesColor = in.readInt();
        titleColor = in.readInt();
        textColor = in.readInt();
        dateColor = in.readInt();
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
        parcel.writeInt(notesColor);
        parcel.writeInt(titleColor);
        parcel.writeInt(textColor);
        parcel.writeInt(dateColor);
    }
}
