package com.example.joelin.greendao001;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joelin.greendao001.Model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joelin on 2017/9/16.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;
    private List<Note> dataset;

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView txvUser;
        public TextView txvDate;
        public TextView txvText;


        public NoteViewHolder(View itemView, final NoteClickListener clickListener) {
            super(itemView);
            txvUser = (TextView) itemView.findViewById(R.id.txvUser);
            txvDate = (TextView) itemView.findViewById(R.id.txvDate);
            txvText = (TextView) itemView.findViewById(R.id.txvText);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public NotesAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<Note>();
    }

    public void setNotes(@NonNull List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.NoteViewHolder holder, int position) {
        Note note = dataset.get(position);
        holder.txvUser.setText(note.getUser());
        holder.txvDate.setText(note.getDate().toString());
        holder.txvText.setText(note.getText());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}