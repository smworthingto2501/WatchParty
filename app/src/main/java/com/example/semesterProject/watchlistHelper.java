package com.example.semesterProject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class watchlistHelper {

    SQLiteDatabase sqLiteDatabase;

    public watchlistHelper(SQLiteDatabase sqLiteDatabase) {

        this.sqLiteDatabase = sqLiteDatabase;

    }

    public void createTable() {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS watchlist " +
                "(id INTEGER PRIMARY KEY, username TEXT, title TEXT, content TEXT, src TEXT)");

    }

    public ArrayList<String> readWatchlist(String username) {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from watchlist where username like '%s'", username), null);

        int titleIndex = c.getColumnIndex("title");

        c.moveToFirst();

        ArrayList<String> watchlist = new ArrayList<>();

        while(!c.isAfterLast()) {
            String currentTitle = c.getString(titleIndex);
            watchlist.add(currentTitle);
            c.moveToNext();

        }

        c.close();
        sqLiteDatabase.close();

        return watchlist;
    }

    public void saveWatchList(String username, String title){
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO watchlist (username, title) VALUES ('%s', '%s')",
                username, title));
    }

    public void updateWatchList(String title) {
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE watchlist set title = '%s'",
                title));
    }

    public void deleteGroup(String title, String username) {
        createTable();
        sqLiteDatabase.execSQL(String.format("DELETE from watchlist where title = '%s' and username = '%s'",
                title, username));
    }

}
