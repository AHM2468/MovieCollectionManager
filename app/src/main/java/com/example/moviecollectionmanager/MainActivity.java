package com.example.moviecollectionmanager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Variables used in the main screen
    private RecyclerView recyclerMovies;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movieList;
    private Button btnAddMovie;
    private Button btnFavorites;
    private SearchView searchView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the views with the XML file
        recyclerMovies = findViewById(R.id.recyclerMovies);
        btnAddMovie = findViewById(R.id.btnAddMovie);
        btnFavorites = findViewById(R.id.btnFavorites);
        searchView = findViewById(R.id.searchView);

        // Load movies from the database
        databaseHelper = new DatabaseHelper(this);
        movieList = databaseHelper.getAllMovies();

        // Set up the RecyclerView
        movieAdapter = new MovieAdapter(movieList);
        recyclerMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerMovies.setAdapter(movieAdapter);

        // Search movies by title
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                movieAdapter.filter(newText);

                return true;
            }
        });

        // Open AddMovieActivity when the button is clicked
        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, AddMovieActivity.class);
                startActivity(myIntent);

            }
        });
        // Open FavoritesActivity when the button is clicked.
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(myIntent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the movie list
        movieList = databaseHelper.getAllMovies();
        movieAdapter.updateList(movieList);
    }
}