package com.example.notesforbots.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notesforbots.domain.NotesEntity;

public class AlertDialogFragment extends DialogFragment {
    private final static String TAG_NOTE_LIST_FRAGMENT = "TAG_NOTE_LIST_FRAGMENT";
    private static final String ENTITY_FROM_NOTE_LIST = "ENTITY_FROM_NOTE_LIST";
    private NotesEntity notesEntity;

    public static AlertDialogFragment newInstance(NotesEntity notesEntity) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ENTITY_FROM_NOTE_LIST, notesEntity);
        alertDialogFragment.setArguments(bundle);
        return alertDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        NotesListFragment notesListFragment = (NotesListFragment) getParentFragmentManager()
                .findFragmentByTag(TAG_NOTE_LIST_FRAGMENT);
        return new AlertDialog.Builder(getContext())
                .setTitle("Предупреждение")
                .setMessage("Вы уверены что хотите удалить эту заметку?")
                .setNegativeButton("Нет", ((dialogInterface, i) -> {
                    notesListFragment.refreshNoteList();
                    dialogInterface.dismiss();
                }))
                .setPositiveButton("Да", ((dialogInterface, i) -> {
                    notesEntity = getArguments().getParcelable(ENTITY_FROM_NOTE_LIST);
                    notesListFragment.onDeleteNote(notesEntity);
                }))
                .setNeutralButton("Cancel", (dialogInterface, i) -> {
                    notesListFragment.refreshNoteList();
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .create();
    }
}
