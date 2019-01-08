package com.example.plainoldnotes.data;

import android.content.Context;
import android.widget.AutoCompleteTextView;

import com.example.plainoldnotes.model.NoteEntity;
import com.example.plainoldnotes.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public List<NoteEntity> mNotes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mNotes = SampleData.getNotes();
        mDb = AppDatabase.getInstance(context);
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAllNotes(SampleData.getNotes());
            }
        });
    }
}
