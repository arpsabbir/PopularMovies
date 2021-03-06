package me.zaicheng.app.popularmovies.di.component;

import dagger.Component;
import me.zaicheng.app.popularmovies.ui.detail.DetailActivity;
import me.zaicheng.app.popularmovies.ui.detail.DetailFragment;
import me.zaicheng.app.popularmovies.ui.main.MoviesActivity;
import me.zaicheng.app.popularmovies.di.PerActivity;
import me.zaicheng.app.popularmovies.di.module.ActivityModule;

/**
 * Created by vmlinz on 3/17/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MoviesActivity moviesActivity);
    void inject(DetailActivity detailActivity);
    void inject(DetailFragment detailFragment);
}
