package com.example.plainoldnotes;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.plainoldnotes.data.AppDatabase;
import com.example.plainoldnotes.data.NoteDao;
import com.example.plainoldnotes.model.NoteEntity;
import com.example.plainoldnotes.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "JUnit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.i(TAG, "createDb: I created a database");
    }

    @After
    public void closeDb(){
        mDb.close();
        Log.i(TAG, "closeDb: I closed the database");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDao.insertAllNotes(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes: count= "+count );
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings(){
        mDao.insertAllNotes(SampleData.getNotes());
        NoteEntity noteEntity = SampleData.getNotes().get(0);
        NoteEntity fromDB = mDao.getNoteById(1);
        assertEquals(noteEntity.getText(), fromDB.getText());
        assertEquals(1, fromDB.getId());
    }
}
