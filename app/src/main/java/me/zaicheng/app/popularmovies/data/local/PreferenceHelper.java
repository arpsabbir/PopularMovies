package me.zaicheng.app.popularmovies.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences.RxSharedPreferences;

import javax.inject.Inject;

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

    private final SharedPreferences mPref;
    private final RxSharedPreferences mRxPref;

    @Inject
    public PreferenceHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mRxPref = RxSharedPreferences.create(mPref);
    }

    public RxSharedPreferences getRxSharedPreference() {
        return mRxPref;
    }

    public SharedPreferences getSharedPreference() {
        return mPref;
    }
}
