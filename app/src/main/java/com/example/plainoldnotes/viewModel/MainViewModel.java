package com.example.plainoldnotes.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.plainoldnotes.data.AppRepository;
import com.example.plainoldnotes.model.NoteEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotes;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }


    public void addSampleData() {
        mRepository.addSampleData();
    }
}
