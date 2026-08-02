package com.example.moviecollectionmanager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    // Variables used in the favorites screen
    private RecyclerView recyclerFavorites;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieList;
    private DatabaseHelper databaseHelper;
    private TextView txtNoFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Connect the RecyclerView with the XML file
        recyclerFavorites = findViewById(R.id.recyclerFavorites);
        txtNoFavorites = findViewById(R.id.txtNoFavorites);

        // Load favorite movies from the database
        databaseHelper = new DatabaseHelper(this);
        movieList = databaseHelper.getFavoriteMovies();

        // Show a message if there are no favorite movies
        if (movieList.isEmpty()) {

            txtNoFavorites.setVisibility(View.VISIBLE);
            recyclerFavorites.setVisibility(View.GONE);

        } else {

            txtNoFavorites.setVisibility(View.GONE);
            recyclerFavorites.setVisibility(View.VISIBLE);

        }

        // Set up the RecyclerView
        movieAdapter = new MovieAdapter(movieList);
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavorites.setAdapter(movieAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Refresh the favorite movie list
        movieList = databaseHelper.getFavoriteMovies();
        movieAdapter.updateList(movieList);
    }
}