package com.example.moviecollectionmanager;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

// This class holds the views for one movie item
public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView txtMovieTitle;
    TextView txtGenre;
    TextView txtYear;
    TextView txtRating;

    // Connect the TextViews with movie_item.xml
    public MovieViewHolder(View itemView) {
        super(itemView);
        txtMovieTitle = itemView.findViewById(R.id.txtMovieTitle);
        txtGenre = itemView.findViewById(R.id.txtGenre);
        txtYear = itemView.findViewById(R.id.txtYear);
        txtRating = itemView.findViewById(R.id.txtRating);
    }
}