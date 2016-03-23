package me.zaicheng.app.popularmovies.ui.main;

import java.util.List;

import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.base.MvpView;

/**
 * Created by vmlinz on 3/23/16.
 */
public interface MoviesMvpView extends MvpView{
    void showMovies(List<Movie> movies);
    void showError();
    void showMoviesEmpty();
}
