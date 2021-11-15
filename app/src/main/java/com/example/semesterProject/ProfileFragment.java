package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

        //@TODO
        //want to show all account info similar to create Account page
        //done with shared preferences?
    }

    //@TODO
    //fix on logout button that sarah broke
    //should be very similar to notes app lab 5
    public void onLogout(View v){
    //    Intent intent = new Intent(this, LoginPage.class);
//                SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
//                sharedPreferences.edit().remove(LoginPage.usernameKey).apply();
//                sharedPreferences.edit().remove(LoginPage.servicesKey).apply();
//                startActivity(intent);
//                return true;
    }
}