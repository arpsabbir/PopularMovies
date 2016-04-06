package me.zaicheng.app.popularmovies.ui.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.data.model.Review;
import me.zaicheng.app.popularmovies.data.model.Trailer;
import me.zaicheng.app.popularmovies.data.remote.MovieResponse;
import me.zaicheng.app.popularmovies.ui.base.BaseActivity;
import me.zaicheng.app.popularmovies.ui.main.MoviesActivity;
import me.zaicheng.app.popularmovies.utils.DialogFactory;
import me.zaicheng.app.popularmovies.utils.MovieUtil;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MoviesActivity}
 * in two-pane mode (on tablets) or a {@link DetailActivity}
 * on handsets.
 */
public class DetailFragment extends Fragment implements DetailMvpView {
    @Inject DetailPresenter mDetailPresenter;
    @Inject ReviewsAdapter mReviewsAdapter;

    @Bind(R.id.tv_movie_detail_overview) TextView mMovieOverview;
    @Bind(R.id.tv_movie_detail_release_date) TextView mMovieReleaseDate;
    @Bind(R.id.tv_movie_detail_vote_average) TextView mMovieVoteAverage;
    @Bind(R.id.tv_movie_detail_title) TextView mMovieTitle;
    @Bind(R.id.image_movie_detail_poster) ImageView mMoviePoster;

    @Bind(R.id.tv_movie_detail_extra_duration) TextView mMovieExtraDuration;
    @Bind(R.id.rv_movie_detail_reviews) RecyclerView mMovieReviews;
    @Bind(R.id.rv_movie_detail_trailers) RecyclerView mMovieTrailers;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = DetailFragment.class.getSimpleName();

    private long movieId = -1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            movieId = getArguments().getLong(ARG_ITEM_ID);
            Log.d(TAG, "onCreate: movie id = " + movieId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        ButterKnife.bind(this, rootView);

        GridLayoutManager glm;
        glm = new GridLayoutManager(this.getActivity(), 2);
        mMovieReviews.setAdapter(mReviewsAdapter);
        mMovieReviews.setLayoutManager(glm);

        mDetailPresenter.attachView(this);

        mDetailPresenter.loadMovie(movieId);
        mDetailPresenter.loadDetails(movieId);
        mDetailPresenter.loadReviews(movieId);
        mDetailPresenter.loadTrailers(movieId);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDetailPresenter.detachView();
    }

    // MvpView Callbacks for Presenter
    @Override
    public void showMovie(Movie movie) {
        mMovieOverview.setText(movie.overview);
        mMovieTitle.setText(movie.title);
        mMovieReleaseDate.setText(movie.releaseDate);
        mMovieVoteAverage.setText(String.valueOf(movie.voteAverage));

        if (movie.posterPath != null) {
            Glide.with(this)
                    .load(MovieUtil.getPosterImageUrl("w185", movie.posterPath))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(mMoviePoster);
        } else {
            Glide.clear(mMoviePoster);
            mMoviePoster.setImageDrawable(null);
        }
    }

    @Override
    public void showTrailers(List<Trailer> trailers) {

    }

    @Override
    public void showReviews(List<Review> reviews) {
        mReviewsAdapter.setReviews(reviews);
        mReviewsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDetails(MovieResponse movieResponse) {
        mMovieExtraDuration.setText(String.valueOf(movieResponse.runtime));
    }

    @Override
    public void showError() {
        DialogFactory.createSimpleOkErrorDialog(getActivity(),
                getString(R.string.error_showing_movie_detail)).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
