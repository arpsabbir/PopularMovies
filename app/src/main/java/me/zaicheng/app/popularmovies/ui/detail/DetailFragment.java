package me.zaicheng.app.popularmovies.ui.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.base.BaseActivity;
import me.zaicheng.app.popularmovies.ui.main.MoviesActivity;
import me.zaicheng.app.popularmovies.utils.DialogFactory;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MoviesActivity}
 * in two-pane mode (on tablets) or a {@link DetailActivity}
 * on handsets.
 */
public class DetailFragment extends Fragment implements DetailMvpView {
    @Inject DetailPresenter mDetailPresenter;

    @Bind(R.id.movie_detail) TextView mMovieDetail;

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
        setRetainInstance(true);
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
        mDetailPresenter.attachView(this);

        mDetailPresenter.loadMovie(movieId);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDetailPresenter.detachView();
    }

    // MvpView Callbacks for Presenter
    @Override
    public void showDetail(Movie movie) {
        mMovieDetail.setText(movie.overview);
    }

    @Override
    public void showError() {
        DialogFactory.createSimpleOkErrorDialog(getActivity(),
                getString(R.string.error_showing_movie_detail)).show();
    }
}
