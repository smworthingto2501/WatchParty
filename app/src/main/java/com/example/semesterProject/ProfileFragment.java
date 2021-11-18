package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        Button logoutButton = (Button) v.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), LoginPage.class);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(LoginPage.usernameKey).apply();
                sharedPreferences.edit().remove(LoginPage.servicesKey).apply();
                startActivity(newIntent);
            }
        });

        Button editProfileButton = (Button) v.findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), CreateAccount.class);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(LoginPage.servicesKey).apply();
                startActivity(newIntent);
            }
        });

        return v;
        //@TODO
        //want to show all account info similar to create Account page
        //done with shared preferences?
    }


    @Override
    public void onClick(View view) {

    }
}