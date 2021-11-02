package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class firstLogin extends AppCompatActivity {

    public static String usernameKey;
    public static String servicesKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameKey = "username";
        servicesKey = "services";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")){
            if(!sharedPreferences.getString(servicesKey,"").equals("")){
                Intent intent = new Intent(this, appHome.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, chooseServices.class);
                startActivity(intent);
            }
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    public void clickFunction(View view) {

        EditText myTextField = (EditText) findViewById(R.id.editTextUsername);
        String str = myTextField.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();

        goToActivity2(str);

    }

    public void goToActivity2(String s) {
        Intent intent = new Intent(this, chooseServices.class);
        intent.putExtra("username", s);
        startActivity(intent);
    }


}