package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class editProfile extends AppCompatActivity {

    String services = "";


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

        if(sharedPreferences.getString("services", "").contains("Netflix")){
            CheckBox netflixBox = findViewById(R.id.netflixBox);
            netflixBox.setChecked(true);
        }

        if(sharedPreferences.getString("services", "").contains("HBOMax")){
            CheckBox hboBox = findViewById(R.id.hboBox);
            hboBox.setChecked(true);
        }

        if(sharedPreferences.getString("services", "").contains("Hulu")){
            CheckBox huluBox = findViewById(R.id.huluBox);
            huluBox.setChecked(true);
        }

        if(sharedPreferences.getString("services", "").contains("AmazonPrime")){
            CheckBox amazonBox = findViewById(R.id.amazonPrime);
            amazonBox.setChecked(true);
        }

    }

    //Create Account Button
    public void clickFunction(View view) {

        CheckBox netflix = findViewById(R.id.netflixBox);
        if(netflix.isChecked()) {
            services = services + " Netflix ";
        }
        CheckBox hbo = findViewById(R.id.hboBox);
        if(hbo.isChecked()) {
            services = services + " HBOMax ";
        }
        CheckBox hulu = findViewById(R.id.huluBox);
        if(hulu.isChecked()) {
            services = services + " Hulu ";
        }
        CheckBox prime = findViewById(R.id.amazonPrime);
        if(prime.isChecked()) {
            services = services + " AmazonPrime ";
        }

        TextView nameView = findViewById(R.id.editTextPersonName);
        String name = nameView.getText().toString();

        TextView usernameView = findViewById(R.id.editTextPersonUsername);
        String username = usernameView.getText().toString();

        TextView passwordView = findViewById(R.id.editTextPersonPassword);
        String password = passwordView.getText().toString();

        SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferencesServices.edit().putString("services", services).apply();
        sharedPreferencesServices.edit().putString("name", name).apply();
        sharedPreferencesServices.edit().putString("username", username).apply();
        sharedPreferencesServices.edit().putString("password", password).apply();
        //goes to homepage
        finish();
    }

}