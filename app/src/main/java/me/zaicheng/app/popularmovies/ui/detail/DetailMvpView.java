package me.zaicheng.app.popularmovies.ui.detail;

import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.base.MvpView;

/**
 * Created by vmlinz on 3/24/16.
 */
public interface DetailMvpView extends MvpView {
    void showDetail(Movie movie);
    void showError();
}
