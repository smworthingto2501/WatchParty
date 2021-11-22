package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


//intent is to start to try to build out swiping mechanics following this tutorial
// https://www.geeksforgeeks.org/tinder-swipe-view-with-example-in-android/
public class Swiping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this would have to change to a swiping page.
        setContentView(R.layout.login_page);

    }

}