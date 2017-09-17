package com.example.joelin.greendao001;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.joelin.greendao001.Model.DaoSession;
import com.example.joelin.greendao001.Model.Note;
import com.example.joelin.greendao001.Model.NoteDao;
import com.example.joelin.greendao001.factory.ServiceFactory;

import org.greenrobot.greendao.query.Query;

import java.util.Calendar;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteDao noteDao;
    private Query<Note> notesQuery;
    private NotesAdapter notesAdapter;

    private EditText txtText;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        setUpViews();

        // get the note DAO
        noteDao = ServiceFactory.getDbService().getNoteDao();

        // query all notes, sorted a-z by their text
        notesQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        updateNotes();
    }

    private void updateNotes() {
        List<Note> notes = notesQuery.list();
        notesAdapter.setNotes(notes);
    }

    protected void setUpViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewNotes);
        //noinspection ConstantConditions
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesAdapter = new NotesAdapter(noteClickListener);
        recyclerView.setAdapter(notesAdapter);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        //noinspection ConstantConditions
        btnAdd.setEnabled(false);

        txtText = (EditText) findViewById(R.id.txtText);
        txtText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });
        txtText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                btnAdd.setEnabled(enable);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }
    private void addNote() {
        try {
            String noteText = txtText.getText().toString();
            txtText.setText("");

            Note note = new Note();
            note.setUser("Default User");
            note.setText(noteText);
            note.setDate(Calendar.getInstance().getTime());
            noteDao.insert(note);
            Log.d("DaoExample", "Inserted new note, ID: " + note.getId());

        }
        catch (Exception ex){
            AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(NoteListActivity.this);
            MyAlertDialog.setTitle("標題");
            MyAlertDialog.setMessage(ex.getMessage());
            MyAlertDialog.show();
        }

        updateNotes();
    }
    NotesAdapter.NoteClickListener noteClickListener = new NotesAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = notesAdapter.getNote(position);
            Long noteId = note.getId();

            noteDao.deleteByKey(noteId);
            Log.d("DaoExample", "Deleted note, ID: " + noteId);

            updateNotes();
        }
    };
}
