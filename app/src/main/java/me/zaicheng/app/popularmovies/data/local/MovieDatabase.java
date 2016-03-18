package me.zaicheng.app.popularmovies.data.local;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by vmlinz on 3/18/16.
 */
@Database(name = MovieDatabase.NAME, version = MovieDatabase.VERSION)
public class MovieDatabase {
    public static final String NAME = "movies";
    public static final int VERSION = 1;
}
