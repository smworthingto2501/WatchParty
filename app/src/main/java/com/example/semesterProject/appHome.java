package com.example.semesterProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;


//hey guys: this might be a good option for the swiping profile
// https://www.geeksforgeeks.org/tinder-swipe-view-with-example-in-android/

public class appHome extends AppCompatActivity {

    private NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        String servicesKey = "services";
        setContentView(R.layout.activity_movie_app);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);


        Fragment onStartFragment = new GroupFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, onStartFragment).commit();
        //@TODO
        //right now this is showing up on all the fragments

        //welcomeMessage.setText("Welcome " + sharedPreferences.getString(usernameKey, "") + ". We know" +
        //        " that you have access to " + sharedPreferences.getString(servicesKey, "") + ".");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("movies", Context.MODE_PRIVATE,null);

    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
                case R.id.group:
                    fragment = new GroupFragment();
                    break;
                case R.id.watchlist:
                    fragment = new WatchlistFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };




}