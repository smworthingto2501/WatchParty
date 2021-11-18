package com.example.semesterProject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GroupFragment extends Fragment {

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group, container, false);

        Button createGroupButton = (Button) v.findViewById(R.id.logoutButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), CreateGroup.class);
                startActivity(newIntent);
            }
        });

        return v;
    }

    //@TODO
    //when group is selected from list view, taken to group watchlist page

    //@TODO
    //create group button has intent for new CreateGroup.java
}