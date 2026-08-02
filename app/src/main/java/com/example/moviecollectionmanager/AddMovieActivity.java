package com.example.moviecollectionmanager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddMovieActivity extends AppCompatActivity {

    // Variables used in the Add Movie screen
    private EditText etMovieTitle;
    private EditText etGenre;
    private EditText etReleaseYear;
    private EditText etRating;
    private EditText etOverview;

    private Button btnSaveMovie;

    // Database helper
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        // Connect the views with the XML file
        etMovieTitle = findViewById(R.id.etMovieTitle);
        etGenre = findViewById(R.id.etGenre);
        etReleaseYear = findViewById(R.id.etReleaseYear);
        etRating = findViewById(R.id.etRating);
        etOverview = findViewById(R.id.etOverview);

        btnSaveMovie = findViewById(R.id.btnSaveMovie);

        // Create the database helper
        databaseHelper = new DatabaseHelper(this);
        // Save the movie when the button is clicked
        btnSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etMovieTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String year = etReleaseYear.getText().toString();
                String rating = etRating.getText().toString();
                String overview = etOverview.getText().toString();

                Movie movie = new Movie(title, genre, year, rating, overview);
                databaseHelper.insertMovie(movie);
                finish();
            }
        });
    }
}