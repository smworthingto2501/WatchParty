package com.example.lab5milestone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewNote extends AppCompatActivity {

    int noteid = -1;

    EditText textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        textView2 = (EditText) findViewById(R.id.noteView);
        Intent intent = getIntent();
        int intentNoteid = intent.getIntExtra("noteid", -1);
        noteid = intentNoteid;

        if(noteid != -1) {
            Note note = NotesApp.notes.get(noteid);
            String noteContent = note.getContent();
            textView2.setText(noteContent);
        }
    }

    public void onClick(View view) {

        String content = textView2.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1) {
            title = "NOTE_" + (NotesApp.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(getApplicationContext(), NotesApp.class);
        startActivity(intent);


    }

}