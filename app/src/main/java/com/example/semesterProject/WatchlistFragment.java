package com.example.semesterProject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WatchlistFragment extends Fragment {

    public WatchlistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //moved this from return statement to up here
        //View v = inflater.inflate(R.layout.fragment_watchlist, container, false);

        //I think this is one way we can link to a new view for swiping
//        Button swipingButton = (Button) v.findViewById(R.id.swipe);
//        swipingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent newIntent = new Intent(getActivity(), Swiping.class);
//                startActivity(newIntent);
//            }
//        });

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false);
    }

    //@TODO
    //right now there is a list view for the movie watchlist, this may be wrong
    //idea is that selected movies could be removed with button in method below...
    public void onClick(View v){

    }
}