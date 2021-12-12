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
import android.widget.TextView;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    View v;
    TextView fullnameView;
    TextView usernameView;
    SharedPreferences sharedPreferences;
    public static TextView faveView;
    TextView genresView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        fullnameView = v.findViewById(R.id.fullName);
        fullnameView.setText(sharedPreferences.getString("name", "").toString());

        usernameView = v.findViewById(R.id.username);
        usernameView.setText(sharedPreferences.getString("username", "").toString());

        faveView = v.findViewById(R.id.favoriteTitle);
        faveView.setText(SwipePage.favoriteMovieTitle);

        genresView = v.findViewById(R.id.genresView);
        genresView.setText(sharedPreferences.getString("genres", "").toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        fullnameView = v.findViewById(R.id.fullName);
        fullnameView.setText(sharedPreferences.getString("name", "").toString());

        usernameView = v.findViewById(R.id.username);
        usernameView.setText(sharedPreferences.getString("username", "").toString());

        genresView = v.findViewById(R.id.genresView);
        genresView.setText(sharedPreferences.getString("genres", "").toString());

        Button logoutButton = (Button) v.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), LoginPage.class);
                sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(LoginPage.usernameKey).apply();
                sharedPreferences.edit().remove(LoginPage.genresKey).apply();
                startActivity(newIntent);
            }
        });

        Button editProfileButton = (Button) v.findViewById(R.id.editProfileButton);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), editProfile.class);
                sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                //sharedPreferences.edit().remove(LoginPage.servicesKey).apply();
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