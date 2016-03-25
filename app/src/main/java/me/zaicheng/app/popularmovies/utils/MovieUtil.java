package me.zaicheng.app.popularmovies.utils;

/**
 * Created by vmlinz on 3/25/16.
 */
public class MovieUtil {
    private static final String POSTER_BASE = "http://image.tmdb.org/t/p/";
    public static String getPosterImageUrl(String size, String path) {
        return POSTER_BASE + size + path;
    }
}
