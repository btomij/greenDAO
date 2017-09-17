package com.example.joelin.greendao001;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.joelin.greendao001.Model.DaoSession;
import com.example.joelin.greendao001.Model.Note;
import com.example.joelin.greendao001.Model.NoteDao;
import com.example.joelin.greendao001.factory.ServiceFactory;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtText;
    Button btnSure;
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtUser = (EditText) findViewById(R.id.txtUser);
        txtText = (EditText) findViewById(R.id.txtText);
        btnSure = (Button) findViewById(R.id.btnSure);
        btnList = (Button) findViewById(R.id.btnList);
        NoteDao noteDao = ServiceFactory.getDbService().getNoteDao();
        List<Note> notes = noteDao.queryBuilder().build().list();
        for(Note note : notes){
            note.getText();
        }
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    // get the note DAO
                    Note note = new Note();
                    note.setUser(txtUser.getText().toString());
                    note.setText(txtText.getText().toString());
                    note.setDate(Calendar.getInstance().getTime());
                    NoteDao noteDao = ServiceFactory.getDbService().getNoteDao();
                    noteDao.insert(note);
                }
                catch (Exception ex){
                    AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(MainActivity.this);
                    MyAlertDialog.setTitle("標題");
                    MyAlertDialog.setMessage(ex.getMessage());
                    MyAlertDialog.show();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteListActivity.class);
                startActivity(intent);
            }
        });
    }
}
