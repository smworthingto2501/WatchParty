package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class editProfile extends AppCompatActivity {

    String genres = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        TextView nameView = findViewById(R.id.editTextPersonName);
        nameView.setText(sharedPreferences.getString("name", "").toString());

        TextView usernameView = findViewById(R.id.editTextPersonUsername);
        usernameView.setText(sharedPreferences.getString("username", "").toString());

        TextView passwordView = findViewById(R.id.editTextPersonPassword);
        passwordView.setText(sharedPreferences.getString("password", "").toString());

        if(sharedPreferences.getString("genres", "").contains("Adventure")){
            CheckBox netflixBox = findViewById(R.id.adventureBox);
            netflixBox.setChecked(true);
        }

        if(sharedPreferences.getString("genres", "").contains("Action")){
            CheckBox hboBox = findViewById(R.id.actionBox);
            hboBox.setChecked(true);
        }

        if(sharedPreferences.getString("genres", "").contains("Comedy")){
            CheckBox huluBox = findViewById(R.id.comedyBox);
            huluBox.setChecked(true);
        }

        if(sharedPreferences.getString("genres", "").contains("Romance")){
            CheckBox amazonBox = findViewById(R.id.romanceBox);
            amazonBox.setChecked(true);
        }

    }

    //Create Account Button
    public void clickFunction(View view) {

        CheckBox netflix = findViewById(R.id.adventureBox);
        if(netflix.isChecked()) {
            genres = genres + " Adventure ";
        }
        CheckBox hbo = findViewById(R.id.actionBox);
        if(hbo.isChecked()) {
            genres = genres + " Action ";
        }
        CheckBox hulu = findViewById(R.id.comedyBox);
        if(hulu.isChecked()) {
            genres = genres + " Comedy ";
        }
        CheckBox prime = findViewById(R.id.romanceBox);
        if(prime.isChecked()) {
            genres = genres + " Romance ";
        }

        TextView nameView = findViewById(R.id.editTextPersonName);
        String name = nameView.getText().toString();

        TextView usernameView = findViewById(R.id.editTextPersonUsername);
        String username = usernameView.getText().toString();

        TextView passwordView = findViewById(R.id.editTextPersonPassword);
        String password = passwordView.getText().toString();

        SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferencesServices.edit().putString("genres", genres).apply();
        sharedPreferencesServices.edit().putString("name", name).apply();
        sharedPreferencesServices.edit().putString("username", username).apply();
        sharedPreferencesServices.edit().putString("password", password).apply();
        //goes to homepage
        finish();
    }

}