package com.example.semesterProject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class swipedListHelper {

    SQLiteDatabase sqLiteDatabase;

    public swipedListHelper(SQLiteDatabase sqLiteDatabase) {

        this.sqLiteDatabase = sqLiteDatabase;

    }

    public void createTable() {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS swipedlist " +
                "(id INTEGER PRIMARY KEY, username TEXT, title TEXT, content TEXT, src TEXT)");

    }

    public ArrayList<String> readWatchlist(String username) {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from swipedList where username like '%s'", username), null);

        int titleIndex = c.getColumnIndex("title");

        c.moveToFirst();

        ArrayList<String> swipedList = new ArrayList<>();

        while(!c.isAfterLast()) {
            String currentTitle = c.getString(titleIndex);
            swipedList.add(currentTitle);
            c.moveToNext();

        }

        c.close();
        sqLiteDatabase.close();

        return swipedList;
    }

    public void saveWatchList(String username, String title){
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO swipedList (username, title) VALUES ('%s', '%s')",
                username, title));
    }

    public void deleteWatchList(String title, String username) {
        createTable();
        sqLiteDatabase.execSQL(String.format("DELETE from swipedList where title = '%s' and username = '%s'",
                title, username));
    }

}
