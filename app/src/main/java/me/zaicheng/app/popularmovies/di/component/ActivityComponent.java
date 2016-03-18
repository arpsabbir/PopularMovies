package me.zaicheng.app.popularmovies.di.component;

import dagger.Component;
import me.zaicheng.app.popularmovies.MovieListActivity;
import me.zaicheng.app.popularmovies.di.PerActivity;
import me.zaicheng.app.popularmovies.di.module.ActivityModule;

/**
 * Created by vmlinz on 3/17/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MovieListActivity movieListActivity);
}
