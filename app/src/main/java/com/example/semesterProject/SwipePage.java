package com.example.semesterProject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chaquo.python.android.AndroidPlatform;
import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.Arrays;

// This is part of the build out for swiping. This class is a part of that process, and follows this tutorial.
// https://www.geeksforgeeks.org/tinder-swipe-view-with-example-in-android/
public class SwipePage extends AppCompatActivity {

    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<SwipePage> movieModalArrayList;
    public static ArrayList<String> watchlist = new ArrayList<>();
    public static String favoriteMovieTitle = "No Favorite";
    public int flag = 0; //flag for set as favorite button being clicked
    public int watched = 0; //flag for if the user has watched or not, used to adjust recommendation

    public SwipePage() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<SwipePage> getRecommendations() {
        // Grab the user swipe list and genre preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabaseSwipes = context.openOrCreateDatabase("swipedList", Context.MODE_PRIVATE, null);
        swipedListHelper swipedList = new swipedListHelper(sqLiteDatabaseSwipes);
        String username = sharedPreferences.getString("username", "");
        ArrayList<String> swipedSuccess = swipedList.readWatchlist(username);
        //sqLiteDatabaseSwipes.close();
        String swipedMovies = String.join("~", swipedSuccess);
        Log.i("SWIPES", swipedMovies);
        String genres = sharedPreferences.getString("genres", "");
        Log.i("GENRES", genres);

        Python python = Python.getInstance();
        PyObject recommend = python.getModule("recommend");
        PyObject movies = recommend.callAttr("get_top_movies", genres, swipedMovies);
        String[] movieTitleArray = movies.get("titles").toJava(String[].class);
        String[] movieRuntimeArray = movies.get("runtimes").toJava(String[].class);
        String[] movieGenreArray = movies.get("genres").toJava(String[].class);
        String[] moviePlotArray = movies.get("plots").toJava(String[].class);
        String[] moviePosterArray = movies.get("posters").toJava(String[].class);
        //movies.close();
        ArrayList<String> movieTitles = new ArrayList<String>(Arrays.asList(movieTitleArray));
        ArrayList<String> movieRuntimes = new ArrayList<String>(Arrays.asList(movieRuntimeArray));
        ArrayList<String> movieGenres = new ArrayList<String>(Arrays.asList(movieGenreArray));
        ArrayList<String> moviePlots = new ArrayList<String>(Arrays.asList(moviePlotArray));
        ArrayList<String> moviePosters = new ArrayList<String>(Arrays.asList(moviePosterArray));

        for (int i=0; i<movieTitles.size(); i++) {
            movieModalArrayList.add(new SwipePage(
                    movieTitles.get(i),
                    movieRuntimes.get(i),
                    movieGenres.get(i),
                    moviePlots.get(i),
                    R.drawable.ic_baseline_back_hand_24));
        }
        return movieModalArrayList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to be the swiping activity. This happens from the swiping button getting
        //pressed in the watch list page. The swiping activity is a shell that holds swiping_item_test instances
        //the data for those gets sets below manually and then gets passed to an adapter that ends up populating the cards and such
        setContentView(R.layout.activity_swiping);

        // TEST.
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        // on below line we are initializing our array list and swipe deck.
        movieModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        movieModalArrayList = getRecommendations();

        // creating a variable for our adapter class and passing array list to it.
        final SwipeAdapter adapter = new SwipeAdapter(movieModalArrayList, this);

        // setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                //if clicked set favorite
                if(flag == 1){
                    favoriteMovieTitle = movieModalArrayList.get(position).getMovieName();
                }
                flag = 0;

                // on card swipe left we are displaying a toast message.
                Toast.makeText(SwipePage.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
                String title = movieModalArrayList.get(position).getMovieName();
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                Context context = getApplicationContext();



                SQLiteDatabase sqLiteDatabaseSwipes = context.openOrCreateDatabase("swipedList", Context.MODE_PRIVATE, null);
                swipedListHelper swipedList = new swipedListHelper(sqLiteDatabaseSwipes);
                swipedList.saveWatchList(username, title);
                ArrayList<String> swipedSuccess = swipedList.readWatchlist(username);

                Log.i("SWIPES", swipedSuccess.toString());
            }

            @Override
            public void cardSwipedRight(int position) {
                //if clicked save to favorites
                if(flag == 1){
                    favoriteMovieTitle = movieModalArrayList.get(position).getMovieName();
                }
                flag = 0;

                // on card swiped to right we are displaying a toast message.
                Toast.makeText(SwipePage.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();
                //movieModalArrayList.add(movieModalArrayList.get(position));

                //this is how we can get data on the movie that they swiped
                String title = movieModalArrayList.get(position).getMovieName();
                Toast.makeText(SwipePage.this, "Card Swiped Right" + ": item: " + title, Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("com.example.semesterProject", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                Context context = getApplicationContext();
                //add movie to watchlist
                if(watched != 1) {

                    SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("watchlist", Context.MODE_PRIVATE, null);
                    watchlistHelper watchlistHelper = new watchlistHelper(sqLiteDatabase);
                    watchlistHelper.saveWatchList(username, title);
                    ArrayList<String> saveSuccess = watchlistHelper.readWatchlist(username);
                    sqLiteDatabase.close();
                    Log.i("MOVIES", saveSuccess.toString());
                }
                watched = 0;




                SQLiteDatabase sqLiteDatabaseSwipes = context.openOrCreateDatabase("swipedList", Context.MODE_PRIVATE, null);
                swipedListHelper swipedList = new swipedListHelper(sqLiteDatabaseSwipes);
                swipedList.saveWatchList(username, title);
                ArrayList<String> swipedSuccess = swipedList.readWatchlist(username);
                //sqLiteDatabaseSwipes.close();

                Log.i("SWIPES", swipedSuccess.toString());

                // Remove from the deck.
                //movieModalArrayList.remove(position);
            }

            @Override
            public void cardsDepleted() {
                //movieModalArrayList = new ArrayList<>();
                movieModalArrayList = getRecommendations();
                // this method is called when no card is present
                Toast.makeText(SwipePage.this, "No more cards present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                // This doesn't accurately report when cards are swiped up --Sarah
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
                // This doesnt accurately report when cards are swiped down --Sarah
                Log.i("TAG", "CARDS MOVED UP");
            }
        });
    }

    // variables for our movies, description, genres and duration, imageId.
    private String movieName;
    private String movieDuration;
    private String movieGenre;
    private String movieDescription;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    private int imgId;

    public static ArrayList<String> getWatchlist() {
        return watchlist;
    }

    // creating getter and setter methods
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public void favoriteFunction(View view) {
        //on click of set as favorite
        flag = 1;
    }

    public void watchedMovie(View view) {
        //on click of Already watched
        watched = 1;
        Toast.makeText(SwipePage.this, "Did you enjoy the movie?", Toast.LENGTH_SHORT).show();
    }




    // constructor.
    public SwipePage(String movieName, String movieDuration, String movieGenre, String movieDescription, int imgId) {
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieGenre = movieGenre;
        this.movieDescription = movieDescription;
        this.imgId = imgId;
    }
}