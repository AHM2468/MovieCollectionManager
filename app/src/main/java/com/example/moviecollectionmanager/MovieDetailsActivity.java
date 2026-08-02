package com.example.moviecollectionmanager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MovieDetailsActivity extends AppCompatActivity implements DeleteDialog.DeleteDialogListener {
    // Variables used in the details screen
    private TextView txtMovieTitle;
    private TextView txtGenre;
    private TextView txtReleaseYear;
    private TextView txtRating;
    private TextView txtOverview;
    private Button btnDeleteMovie;
    private Button btnFavorite;
    private int favoriteStatus;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Connect the views with the XML file
        txtMovieTitle = findViewById(R.id.txtMovieTitle);
        txtGenre = findViewById(R.id.txtGenre);
        txtReleaseYear = findViewById(R.id.txtReleaseYear);
        txtRating = findViewById(R.id.txtRating);
        txtOverview = findViewById(R.id.txtOverview);
        btnDeleteMovie = findViewById(R.id.btnDeleteMovie);
        btnFavorite = findViewById(R.id.btnFavorite);

        // Create the database helper
        databaseHelper = new DatabaseHelper(this);

        // Display the selected movie information
        txtMovieTitle.setText(getIntent().getStringExtra("title"));
        txtGenre.setText(getIntent().getStringExtra("genre"));
        txtReleaseYear.setText(getIntent().getStringExtra("year"));
        txtRating.setText(getIntent().getStringExtra("rating"));
        txtOverview.setText(getIntent().getStringExtra("overview"));
        favoriteStatus = getIntent().getIntExtra("favorite", 0);
        if (favoriteStatus == 1) {

            btnFavorite.setText("Remove from Favorites");

        } else {

            btnFavorite.setText("Add to Favorites");

        }

        // Add or remove the movie from favorites
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = getIntent().getStringExtra("title");

                if (favoriteStatus == 0) {

                    databaseHelper.updateFavorite(title, 1);
                    favoriteStatus = 1;
                    btnFavorite.setText("Remove from Favorites");

                } else {

                    databaseHelper.updateFavorite(title, 0);
                    favoriteStatus = 0;
                    btnFavorite.setText("Add to Favorites");

                }

            }
        });

        // Show the delete confirmation dialog
        btnDeleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DeleteDialog deleteDialog = new DeleteDialog();
                deleteDialog.setDeleteDialogListener(MovieDetailsActivity.this);
                deleteDialog.show(fragmentManager, "Delete Dialog");
            }
        });
    }
    @Override
    public void onDeleteConfirmed() {
        String title = getIntent().getStringExtra("title");
        databaseHelper.deleteMovie(title);
        finish();
    }
}