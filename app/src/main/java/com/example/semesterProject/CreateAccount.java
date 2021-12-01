package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    //Create Account Button
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

        TextView nameView = findViewById(R.id.editTextPersonName);
        String name = nameView.getText().toString();

        TextView emailView = findViewById(R.id.editTextPersonEmail);
        String email = emailView.getText().toString();

        TextView usernameView = findViewById(R.id.editTextPersonUsername);
        String username = usernameView.getText().toString();

        TextView passwordView = findViewById(R.id.editTextPersonPassword);
        String password = passwordView.getText().toString();

        SharedPreferences sharedPreferencesServices = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferencesServices.edit().putString("services", services).apply();
        sharedPreferencesServices.edit().putString("name", name).apply();
        sharedPreferencesServices.edit().putString("email", email).apply();
        sharedPreferencesServices.edit().putString("username", username).apply();
        sharedPreferencesServices.edit().putString("password", password).apply();

        //goes to homepage
        Intent intent = new Intent(this, appHome.class);
        startActivity(intent);
    }

}