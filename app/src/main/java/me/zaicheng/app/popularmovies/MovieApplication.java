package me.zaicheng.app.popularmovies;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by vmlinz on 3/18/16.
 */
public class MovieApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
