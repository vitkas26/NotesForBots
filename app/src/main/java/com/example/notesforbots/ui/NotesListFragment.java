package com.example.notesforbots.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesforbots.App;
import com.example.notesforbots.R;
import com.example.notesforbots.domain.NotesEntity;
import com.example.notesforbots.domain.NotesRepo;
import com.example.notesforbots.ui.adapter.NotesAdapter;
import com.example.notesforbots.ui.adapter.SwipeCallback;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class NotesListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NotesRepo notesRepo;
    private Controller controller;

    public interface Controller {
        void createNewNote();

        void showNote(NotesEntity notesEntity);
    }

    public NotesListFragment() {
    }

    //controller is used when you want to open another fragment from main activity with noteItem
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NotesListFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        notesRepo = App.get().localNotesRepo;
        initToolbar();
        initRecyclerView(view);
        initSwapAction();
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.notes_list_fragment__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notesAdapter = new NotesAdapter();
        notesAdapter.setOnClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onClickItem(NotesEntity notesEntity) {
                controller.showNote(notesEntity);
            }

            @Override
            public void onDeleteItem(NotesEntity notesEntity) {
                AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance(notesEntity);
                alertDialogFragment.show(requireActivity().getSupportFragmentManager(), "deleteDialogue");
            }

            @Override
            public void onRefreshItem(NotesEntity notesEntity) {
                String message;
                if (notesRepo.findPosition(notesEntity) != 0) {
                    message = "Заметка поднята вверх";
                } else {
                    message = "Заметка на самом верху";
                }
                Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Закрыть", snackView -> {
                    snackbar.dismiss();
                })
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                        .setActionTextColor(Color.YELLOW)
                        .setTextColor(Color.WHITE)
                        .setBackgroundTint(Color.BLACK)
                        .show();
                notesRepo.swapNote(notesEntity);
                notesAdapter.setData(notesRepo.getNotes());
            }
        });
        notesAdapter.setData(notesRepo.getNotes());
        recyclerView.setAdapter(notesAdapter);
    }

    private void initSwapAction() {
        ItemTouchHelper.Callback callback = new SwipeCallback(notesAdapter, requireActivity());
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    public void onNoteCreated(NotesEntity notesEntity) {
        notesRepo.addNote(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }

    public void onDeleteNote(NotesEntity notesEntity) {
        notesRepo.deleteNote(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }

    public void onNoteEdited(NotesEntity notesEntity) {
        notesRepo.editNotes(notesEntity);
        notesAdapter.setData(notesRepo.getNotes());
    }

    public void refreshNoteList(){
        notesAdapter.setData(notesRepo.getNotes());
    }

    private void initToolbar() {
        final androidx.appcompat.widget.Toolbar toolbar = getView().findViewById(R.id.notes_list_fragment__toolbar);
        toolbar.setTitle("Заметки");
        initMenu(toolbar);
    }

    private void initMenu(Toolbar toolbar){
        final MenuInflater menuInflater = getActivity().getMenuInflater();
        final Menu menu = toolbar.getMenu();
        menuInflater.inflate(R.menu.list_note_menu, menu);
        menu.findItem(R.id.list_note_menu__menu_add).setOnMenuItemClickListener(v->{
            controller.createNewNote();
            return true;
        });
    }
}
