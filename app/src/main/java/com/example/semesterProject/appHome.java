package com.example.semesterProject;

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

public class appHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        String servicesKey = "services";
        setContentView(R.layout.activity_notes_app);


        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        TextView welcomeMessage = findViewById(R.id.textView);
        welcomeMessage.setText("Welcome " + sharedPreferences.getString(usernameKey, "") + ". We know" +
                " that you have access to " + sharedPreferences.getString(servicesKey, "") + ".");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("movies", Context.MODE_PRIVATE,null);

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
                Intent intent = new Intent(this, firstLogin.class);
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(firstLogin.usernameKey).apply();
                sharedPreferences.edit().remove(firstLogin.servicesKey).apply();
                startActivity(intent);
                return true;
            case R.id.streaming:
                Intent intentChangeStreaming = new Intent(this, chooseServices.class);
                SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                sharedPreferencesServices.edit().remove(firstLogin.servicesKey).apply();
                startActivity(intentChangeStreaming);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}