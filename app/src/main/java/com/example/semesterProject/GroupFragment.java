package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class GroupFragment extends Fragment {

    public static ArrayList<Group> groups = new ArrayList<>();

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_group, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        Context context = getActivity();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("groups", Context.MODE_PRIVATE,null);

        groupsHelper groupHelper = new groupsHelper(sqLiteDatabase);
        groups = groupHelper.readGroups(sharedPreferences.getString("username", ""));

        ArrayList<String> displayGroups = new ArrayList<>();
        for (Group group : groups) {
            displayGroups.add(String.format("Title:%s\nContent:%s", group.getTitle(), group.getContent()));
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, displayGroups);
        ListView listView = (ListView) v.findViewById(R.id.groupList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), CreateGroup.class);
                intent.putExtra("groupid", i);
                startActivity(intent);
            }
        });

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