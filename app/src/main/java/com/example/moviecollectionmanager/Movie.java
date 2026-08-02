package com.example.moviecollectionmanager;

// This class stores the information for each movie
public class Movie {

    private String title;
    private String genre;
    private String year;
    private String rating;
    private String overview;
    private int favorite;

    // Constructor to create a new movie object
    public Movie(String title, String genre, String year, String rating, String overview) {

        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.overview = overview;
        this.favorite = 0;

    }

    // Getter methods to access the movie information
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}