package com.example.moviecollectionmanager;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "MovieCollection.db";
    private static final int DATABASE_VERSION = 2;

    // Table name
    private static final String TABLE_MOVIES = "movies";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_OVERVIEW = "overview";
    private static final String COLUMN_FAVORITE = "favorite";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the movies table
        String createTable = "CREATE TABLE " + TABLE_MOVIES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_GENRE + " TEXT, " +
                COLUMN_YEAR + " TEXT, " +
                COLUMN_RATING + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT, " +
                COLUMN_FAVORITE + " INTEGER DEFAULT 0)";
        db.execSQL(createTable);

        // Insert sample movies
        ContentValues movie1 = new ContentValues();
        movie1.put(COLUMN_TITLE, "Interstellar");
        movie1.put(COLUMN_GENRE, "Science Fiction");
        movie1.put(COLUMN_YEAR, "2014");
        movie1.put(COLUMN_RATING, "8.7");
        movie1.put(COLUMN_OVERVIEW,
                "A team of astronauts travels through a wormhole to find a new home for humanity.");
        movie1.put(COLUMN_FAVORITE, 0);
        db.insert(TABLE_MOVIES, null, movie1);

        ContentValues movie2 = new ContentValues();
        movie2.put(COLUMN_TITLE, "The Dark Knight");
        movie2.put(COLUMN_GENRE, "Action");
        movie2.put(COLUMN_YEAR, "2008");
        movie2.put(COLUMN_RATING, "9.0");
        movie2.put(COLUMN_OVERVIEW,
                "Batman faces the Joker, a criminal mastermind who spreads chaos across Gotham City.");
        movie2.put(COLUMN_FAVORITE, 0);
        db.insert(TABLE_MOVIES, null, movie2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Delete the old table and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    // Save a new movie into the database
    public void insertMovie(Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATING, movie.getRating());
        values.put(COLUMN_OVERVIEW, movie.getOverview());
        values.put(COLUMN_FAVORITE, 0);

        db.insert(TABLE_MOVIES, null, values);

        db.close();

    }

    // Read all movies from the database
    public ArrayList<Movie> getAllMovies() {

        ArrayList<Movie> movieList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIES, null);

        if (cursor.moveToFirst()) {

            do {

                Movie movie = new Movie(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
                movie.setFavorite(cursor.getInt(6));
                movieList.add(movie);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return movieList;

    }

    // Delete a movie using its title
    public void deleteMovie(String title) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MOVIES, COLUMN_TITLE + " = ?", new String[]{title});

        db.close();

    }

    // Update the favorite status of a movie
    public void updateFavorite(String title, int favorite) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FAVORITE, favorite);

        db.update(TABLE_MOVIES,
                values,
                COLUMN_TITLE + " = ?",
                new String[]{title});

        db.close();

    }

    // Read only favorite movies from the database
    public ArrayList<Movie> getFavoriteMovies() {

        ArrayList<Movie> movieList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_MOVIES +
                        " WHERE " + COLUMN_FAVORITE + " = 1", null);

        if (cursor.moveToFirst()) {

            do {

                Movie movie = new Movie(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));

                movie.setFavorite(cursor.getInt(6));

                movieList.add(movie);

            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();

        return movieList;

    }
}