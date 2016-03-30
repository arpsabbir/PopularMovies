package me.zaicheng.app.popularmovies.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.di.ApplicationContext;

/**
 * Created by vmlinz on 3/29/16.
 */
public class PreferenceHelper {
    public static final String PREF_FILE_NAME = "popular_movies_pref_file";
    public static final String PREF_KEY_MOVIE_ORDER = "PREF_KEY_MOVIE_ORDER";
    public static final String PREF_VALUE_MOVIE_ORDER_POPULAR = "popular";
    public static final String PREF_VALUE_MOVIE_ORDER_TOP_RATED = "top_rated";
    public static final String PREF_VALUE_MOVIE_ORDER_FAVORITE = "favorite";
    public static final String PREF_KEY_FAVORITE_MOVIES = "PREF_KEY_FAVORITE_MOVIES";

    private final SharedPreferences mPref;
    private final RxSharedPreferences mRxPref;
    private final Gson mGson;

    @Inject
    public PreferenceHelper(@ApplicationContext Context context, Gson gson) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mRxPref = RxSharedPreferences.create(mPref);
        mGson = gson;
    }

    /**
     * get the instance of RxSharedPreference
     * @return an instance of RxSharedPreference
     */
    public RxSharedPreferences getRxSharedPreference() {
        return mRxPref;
    }

    /**
     * get the instance of shared preference
     * @return an instance of SharedPreference
     */
    public SharedPreferences getSharedPreference() {
        return mPref;
    }

    /**
     * add one movie to favorite movies
     * @param movie the movie to be saved
     */
    public void addMovieToFavorites(Movie movie) {
        Type type = new TypeToken<List<Movie>>() {}.getType();
        String moviesString = mPref.getString(PREF_KEY_FAVORITE_MOVIES, "");
        List<Movie> movieList;
        if (!moviesString.equals("")) {
            movieList = mGson.fromJson(moviesString, type);

            for (Movie m : movieList) {
                if (m.tmdb_id == movie.tmdb_id) {
                    movieList.remove(m);
                }
            }
        } else {
            movieList = new ArrayList<>();
        }

        movieList.add(movie);

        mPref.edit()
                .putString(PREF_KEY_FAVORITE_MOVIES, mGson.toJson(movieList))
                .apply();
    }

    /**
     * remove one movie from favorite movies
     * @param movie the movie to be removed
     */
    public void removeMovieFromFavorites(Movie movie) {
        Type type = new TypeToken<List<Movie>>() {}.getType();
        String moviesString = mPref.getString(PREF_KEY_FAVORITE_MOVIES, "");
        List<Movie> movieList = mGson.fromJson(moviesString, type);

        for (Movie m : movieList) {
            if (m.tmdb_id == movie.tmdb_id) {
                movieList.remove(m);
            }
        }

        mPref.edit()
                .putString(PREF_KEY_FAVORITE_MOVIES, mGson.toJson(movieList))
                .apply();
    }

    /**
     * clear favorite movies
     */
    public void clearMoivesFromFavorites() {
        mPref.edit()
                .putString(PREF_KEY_FAVORITE_MOVIES, "")
                .apply();
    }

    /**
     * save movies to favorite and override old ones
     * @param movies a list of movies
     */
    public void saveMoviesToFavorites(List<Movie> movies) {
        mPref.edit()
                .putString(PREF_KEY_FAVORITE_MOVIES, mGson.toJson(movies))
                .apply();
    }

    public List<Movie> getMoviesFromFavorites() {
        Type type = new TypeToken<List<Movie>>() {}.getType();
        return mGson.fromJson(mPref.getString(PREF_KEY_FAVORITE_MOVIES, ""), type);
    }
}
