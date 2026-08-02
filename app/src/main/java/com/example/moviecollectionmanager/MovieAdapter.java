package com.example.moviecollectionmanager;
import android.content.Intent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;

// This class displays the movie list in the RecyclerView
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<Movie> movieList;
    private ArrayList<Movie> movieListFull;

    // Constructor to receive the movie list
    public MovieAdapter(ArrayList<Movie> movieList) {

        this.movieList = movieList;
        this.movieListFull = new ArrayList<>(movieList);

    }

    // Create the layout for each movie item
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(view);
    }

    // Display movie information inside each item
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieList.get(position);

        holder.txtMovieTitle.setText(movie.getTitle());
        holder.txtGenre.setText(movie.getGenre());
        holder.txtYear.setText(movie.getYear());
        holder.txtRating.setText(movie.getRating());

        // Open the movie details screen when an item is clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();

                Intent intent = new Intent(context, MovieDetailsActivity.class);

                intent.putExtra("title", movie.getTitle());
                intent.putExtra("genre", movie.getGenre());
                intent.putExtra("year", movie.getYear());
                intent.putExtra("rating", movie.getRating());
                intent.putExtra("overview", movie.getOverview());
                intent.putExtra("favorite", movie.getFavorite());

                context.startActivity(intent);

            }
        });
    }

    // Return the number of movies
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // Filter movies by title
    public void filter(String text) {

        movieList.clear();

        if (text.isEmpty()) {

            movieList.addAll(movieListFull);

        } else {

            text = text.toLowerCase(Locale.getDefault());

            for (Movie movie : movieListFull) {

                if (movie.getTitle().toLowerCase(Locale.getDefault()).contains(text)) {

                    movieList.add(movie);

                }

            }

        }

        notifyDataSetChanged();

    }

    // Update the movie list after adding or deleting movies
    public void updateList(ArrayList<Movie> newList) {
        movieList.clear();
        movieList.addAll(newList);
        movieListFull.clear();
        movieListFull.addAll(newList);
        notifyDataSetChanged();
    }
}