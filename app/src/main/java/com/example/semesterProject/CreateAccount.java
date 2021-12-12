package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    //Create Account Button
    public void clickFunction(View view) {

        String genres = "";

        CheckBox adventure = findViewById(R.id.adventureBox);
        if(adventure.isChecked()) {
            genres = genres + " Adventure ";
        }
        CheckBox action = findViewById(R.id.actionBox);
        if(action.isChecked()) {
            genres = genres + " Action ";
        }
        CheckBox comedy = findViewById(R.id.comedyBox);
        if(comedy.isChecked()) {
            genres = genres + " Comedy ";
        }
        CheckBox romance = findViewById(R.id.romanceBox);
        if(romance.isChecked()) {
            genres = genres + " Romance ";
        }

        TextView nameView = findViewById(R.id.editTextPersonName);
        String name = nameView.getText().toString();

        TextView emailView = findViewById(R.id.editTextPersonEmail);
        String email = emailView.getText().toString();

        TextView usernameView = findViewById(R.id.editTextPersonUsername);
        String username = usernameView.getText().toString();

        TextView passwordView = findViewById(R.id.editTextPersonPassword);
        String password = passwordView.getText().toString();

        SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferencesServices.edit().putString("genres", genres).apply();
        sharedPreferencesServices.edit().putString("name", name).apply();
        sharedPreferencesServices.edit().putString("email", email).apply();
        sharedPreferencesServices.edit().putString("username", username).apply();
        sharedPreferencesServices.edit().putString("password", password).apply();

        //goes to homepage
        Intent intent = new Intent(this, appHome.class);
        startActivity(intent);
    }

}