package com.example.semesterProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;


// README:
// This is part of the build out for swiping. This class is a part of that process, and follows this tutorial.
// https://www.geeksforgeeks.org/tinder-swipe-view-with-example-in-android/
public class SwipePage extends AppCompatActivity {

    // on below line we are creating variable
    // for our array list and swipe deck.
    private SwipeDeck cardStack;
    private ArrayList<SwipePage> movieModalArrayList;

    public SwipePage() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the view to be the swiping activity. This happens from the swiping button getting
        //pressed in the watch list page. The swiping activity is a shell that holds swiping_item_test instances
        //the data for those gets sets below manually and then gets passed to an adapter that ends up populating the cards and such
        setContentView(R.layout.activity_swiping);

        // on below line we are initializing our array list and swipe deck.
        movieModalArrayList = new ArrayList<>();
        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);

        // on below line we are adding data to our array list.
        //we would probably alter this to loop through API data
        movieModalArrayList.add(new SwipePage("Titanic", "194 min", "Drama, Romance",
                "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic", R.drawable.ic_baseline_back_hand_24));
        movieModalArrayList.add(new SwipePage("E.T. the Extra-Terrestrial", "115 min", "Adventure, Family, Sci-Fi",
                "A troubled child summons the courage to help a friendly alien escape Earth and return to his home world.", R.drawable.ic_baseline_back_hand_24));
        movieModalArrayList.add(new SwipePage("The Wizard of Oz", "102 min", "Adventure, Family, Fantasy",
                "Young Dorothy Gale and her dog are swept away by a tornado from their Kansas farm to the magical Land of Oz, " +
                              "and embark on a quest with three new friends to see the Wizard, who can return her to her home and fulfill the others' wishes.", R.drawable.ic_baseline_back_hand_24));
        movieModalArrayList.add(new SwipePage("The Lion King", "88 min", "Animation, Adventure, Drama",
                "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.", R.drawable.ic_baseline_back_hand_24));
        movieModalArrayList.add(new SwipePage("The Godfather", "175 min", "Crime, Drama",
                "The Godfather follows Vito Corleone Don of the Corleone family as he passes the mantel to his son Michael.", R.drawable.ic_baseline_back_hand_24));

        // creating a variable for our adapter class and passing array list to it.
        final SwipeAdapter adapter = new SwipeAdapter(movieModalArrayList, this);

        // setting adapter to our card stack.
        cardStack.setAdapter(adapter);

        // on below line we are setting event callback to our card stack.
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                // on card swipe left we are displaying a toast message.
                Toast.makeText(SwipePage.this, "Card Swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(int position) {
                // on card swiped to right we are displaying a toast message.
                Toast.makeText(SwipePage.this, "Card Swiped Right", Toast.LENGTH_SHORT).show();

                //this is how we can get data on the movie that they swiped
                String title = movieModalArrayList.get(position).getMovieName();
                Toast.makeText(SwipePage.this, "Card Swiped Right" + ": item: " + title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(SwipePage.this, "No more courses present", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardActionDown() {
                // this method is called when card is swiped down.
                Log.i("TAG", "CARDS MOVED DOWN");
            }

            @Override
            public void cardActionUp() {
                // this method is called when card is moved up.
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

    // constructor.
    public SwipePage(String movieName, String movieDuration, String movieGenre, String movieDescription, int imgId) {
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieGenre = movieGenre;
        this.movieDescription = movieDescription;
        this.imgId = imgId;
    }
}