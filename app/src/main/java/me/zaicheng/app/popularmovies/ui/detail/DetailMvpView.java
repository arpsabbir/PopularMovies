package me.zaicheng.app.popularmovies.ui.detail;

import java.util.List;

import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.data.model.Review;
import me.zaicheng.app.popularmovies.data.model.Trailer;
import me.zaicheng.app.popularmovies.data.remote.MovieResponse;
import me.zaicheng.app.popularmovies.ui.base.MvpView;

/**
 * Created by vmlinz on 3/24/16.
 */
public interface DetailMvpView extends MvpView {
    void showMovie(Movie movie);

    void showTrailers(List<Trailer> trailers);

    void showReviews(List<Review> reviews);

    void showError();

    void showDetails(MovieResponse movieResponse);
}
