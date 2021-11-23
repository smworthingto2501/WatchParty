package com.example.semesterProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SwipeAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<SwipePage> movieData;
    private Context context;

    // on below line we have created constructor for our variables.
    public SwipeAdapter(ArrayList<SwipePage> movieData, Context context) {
        this.movieData = movieData;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return movieData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.swiping_item, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.idTVMovieName)).setText(movieData.get(position).getMovieName());
        ((TextView) v.findViewById(R.id.idTVMovieDescription)).setText(movieData.get(position).getMovieDescription());
        ((TextView) v.findViewById(R.id.idTVMovieDuration)).setText(movieData.get(position).getMovieDuration());
        ((TextView) v.findViewById(R.id.idTVMovieGenres)).setText(movieData.get(position).getMovieGenre());
        ((ImageView) v.findViewById(R.id.idIVMovie)).setImageResource(movieData.get(position).getImgId());
        return v;
    }
}

