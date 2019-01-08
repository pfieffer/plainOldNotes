package com.example.plainoldnotes.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.plainoldnotes.model.NoteEntity;
import com.example.plainoldnotes.utilities.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotes = SampleData.getNotes();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


}
