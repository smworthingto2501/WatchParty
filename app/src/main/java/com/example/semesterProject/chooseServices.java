package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class chooseServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_services);
    }

    public void clickFunction(View view) {

        String services = "";

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


        //EditText myTextField = (EditText) findViewById(R.id.editTextUsername);
        //String str = myTextField.getText().toString();

        //SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5milestone1", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username", str).apply();

        //String s = "test";
        SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferencesServices.edit().putString("services", services).apply();

        Intent intent = new Intent(this, appHome.class);
        //intent.putExtra("username", s);
        startActivity(intent);

    }

}