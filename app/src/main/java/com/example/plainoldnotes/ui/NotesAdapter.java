package com.example.plainoldnotes.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.plainoldnotes.EditorActivity;
import com.example.plainoldnotes.R;
import com.example.plainoldnotes.model.NoteEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.plainoldnotes.utilities.Constants.NOTE_ID_KEY;

public class NotesAdapter extends RecyclerView.Adapter <NotesAdapter.ViewHolder>{

    private final List<NoteEntity> mNotes;
    private final Context mContext;

    public NotesAdapter(List<NoteEntity> mNotes, Context mContext) {
        this.mNotes = mNotes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_note_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NoteEntity note = mNotes.get(i);
        viewHolder.mTextView.setText(note.getText());

        viewHolder.mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditorActivity.class);
                intent.putExtra(NOTE_ID_KEY, note.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        TextView mTextView;
        @BindView(R.id.floatingActionButton)
        FloatingActionButton mFab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
