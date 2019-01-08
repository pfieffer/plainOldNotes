package com.example.plainoldnotes.data;

import com.example.plainoldnotes.model.NoteEntity;
import com.example.plainoldnotes.utilities.SampleData;

import java.util.List;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();

    public List<NoteEntity> mNotes;

    public static AppRepository getInstance() {
        return ourInstance;
    }

    private AppRepository() {
        mNotes = SampleData.getNotes();
    }
}
