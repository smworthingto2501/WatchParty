package com.example.lab5milestone2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesApp extends AppCompatActivity {

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        setContentView(R.layout.activity_notes_app);


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);

        TextView welcomeMessage = findViewById(R.id.textView);
        welcomeMessage.setText("Welcome " + sharedPreferences.getString(usernameKey, ""));

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);

        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        notes = dbHelper.readNotes(sharedPreferences.getString(usernameKey, ""));

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NewNote.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(this, MainActivity.class);
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(MainActivity.usernameKey).apply();
                startActivity(intent);
                return true;
            case R.id.addNote:
                Intent intentAddNote = new Intent(this, NewNote.class);
                startActivity(intentAddNote);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}