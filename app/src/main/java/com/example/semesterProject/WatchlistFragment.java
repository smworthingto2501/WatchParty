package com.example.semesterProject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class WatchlistFragment extends Fragment {

    public static ArrayList<String> movies = new ArrayList<>();
    public static View v;

    public WatchlistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //moved this from return statement to up here
        v = inflater.inflate(R.layout.fragment_watchlist, container, false);

        // onClick of swiping view button, open swiping view activity from swiping class
        Button swipingButton = (Button) v.findViewById(R.id.swipe);
        swipingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(getActivity(), SwipePage.class);
                startActivity(newIntent);
            }
        });

       // movies = SwipePage.getWatchlist();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

        Context context = getActivity();
        //context.deleteDatabase("watchlist");
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("watchlist", Context.MODE_PRIVATE,null);

        watchlistHelper watchlistHelper = new watchlistHelper(sqLiteDatabase);
        movies = watchlistHelper.readWatchlist(sharedPreferences.getString("username", ""));
        ArrayList<String> displayGroups = new ArrayList<>();
        for (String title : movies) {
            displayGroups.add(String.format(title));
            Log.i("MOVIES", title);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, displayGroups);
        ListView listView = (ListView) v.findViewById(R.id.watchlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);

                Context context = getActivity();

                SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("watchlist", Context.MODE_PRIVATE,null);
                watchlistHelper watchlistHelper = new watchlistHelper(sqLiteDatabase);
                watchlistHelper.deleteGroup(item, sharedPreferences.getString("username", ""));
                sqLiteDatabase.close();

                SQLiteDatabase sqLiteDatabaseSwipes = context.openOrCreateDatabase("swipedList", Context.MODE_PRIVATE, null);
                swipedListHelper swipedList = new swipedListHelper(sqLiteDatabaseSwipes);
                swipedList.deleteWatchList(item, sharedPreferences.getString("username", ""));
                sqLiteDatabaseSwipes.close();

                onResume();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    //@TODO
    //right now there is a list view for the movie watchlist, this may be wrong
    //idea is that selected movies could be removed with button in method below...
    public void onClick(View v){

    }

    @Override
    public void onResume(){
        super.onResume();
        Context context = getActivity();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("watchlist", Context.MODE_PRIVATE,null);

        watchlistHelper watchlistHelper = new watchlistHelper(sqLiteDatabase);
        movies = watchlistHelper.readWatchlist(sharedPreferences.getString("username", ""));
        ArrayList<String> displayGroups = new ArrayList<>();
        for (String title : movies) {
            displayGroups.add(String.format(title));
            Log.i("MOVIES", title);
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, displayGroups);
        ListView listView = (ListView) v.findViewById(R.id.watchlist);
        listView.setAdapter(adapter);
    }
}