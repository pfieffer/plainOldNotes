package com.example.plainoldnotes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.plainoldnotes.model.NoteEntity;
import com.example.plainoldnotes.viewModel.EditorViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.plainoldnotes.utilities.Constants.NOTE_ID_KEY;

public class EditorActivity extends AppCompatActivity {

    @BindView(R.id.et_note_text)
    EditText mEditText;

    private EditorViewModel mViewModel;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {
                if (noteEntity != null) {
                    mEditText.setText(noteEntity.getText());
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            setTitle("New Note");
            mNewNote = true;
        } else {
            setTitle("Edit Note");
            int noteId = extras.getInt(NOTE_ID_KEY);
            mViewModel.loadData(noteId);

        }
    }
}
