package com.example.semesterProject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class groupsHelper {

    SQLiteDatabase sqLiteDatabase;

    public groupsHelper(SQLiteDatabase sqLiteDatabase) {

        this.sqLiteDatabase = sqLiteDatabase;

    }

    public void createTable() {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS groups " +
                "(id INTEGER PRIMARY KEY, username TEXT, title TEXT, content TEXT, src TEXT)");

    }

    public ArrayList<Group> readGroups(String username) {
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from groups where username like '%s'", username), null);

        int titleIndex = c.getColumnIndex("title");
        int contentIndex = c.getColumnIndex("content");

        c.moveToFirst();

        ArrayList<Group> groupsList = new ArrayList<>();

        while(!c.isAfterLast()) {
            String title = c.getString(titleIndex);
            String content = c.getString(contentIndex);

            Group group = new Group(username, title, content);
            groupsList.add(group);
            c.moveToNext();

        }

        c.close();
        sqLiteDatabase.close();

        return groupsList;
    }

    public void saveGroups(String username, String title, String content){
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO groups (username, title, content) VALUES ('%s', '%s', '%s')",
                username, title, content));
    }

    public void updateGroup(String title, String content, String username) {
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE groups set content = '%s' where title = '%s' and username = '%s'",
                content, title, username));
    }

}
