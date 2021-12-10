package com.example.semesterProject;

import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;
import java.util.Arrays;

public class MovieInfo {

    private ArrayList<String> userMovies;
    private ArrayList<String> movieRuntimes;

    public MovieInfo() {
        Python python = Python.getInstance();
        PyObject recommend = python.getModule("recommend");
        PyObject movies = recommend.callAttr("get_top_movies");

        // Movie titles.
        String[] movieTitleArray = movies.get("titles").toJava(String[].class);
        ArrayList<String> movieTitles = new ArrayList<String>(Arrays.asList(movieTitleArray));
        // Movie runtime.
        String[] movieRuntimeArray = movies.get("runtimes").toJava(String[].class);
        ArrayList<String> movieRuntimes = new ArrayList<String>(Arrays.asList(movieRuntimeArray));
        // Movie genre.
        String[] movieGenreArray = movies.get("genres").toJava(String[].class);
        ArrayList<String> movieGenres = new ArrayList<String>(Arrays.asList(movieGenreArray));
        // Movie plot.
        String[] moviePlotArray = movies.get("plots").toJava(String[].class);
        ArrayList<String> moviePlots = new ArrayList<String>(Arrays.asList(moviePlotArray));
        // Movie poster.
        String[] moviePosterArray = movies.get("plots").toJava(String[].class);
        ArrayList<String> moviePosters = new ArrayList<String>(Arrays.asList(moviePosterArray));

    }

}
