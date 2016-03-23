package me.zaicheng.app.popularmovies.ui.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.Select;

import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.data.model.Movie_Table;
import me.zaicheng.app.popularmovies.ui.main.MoviesActivity;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MoviesActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = MovieDetailFragment.class.getSimpleName();

    /**
     * The dummy content this fragment is presenting.
     */
    private long movieId = -1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            movieId = getArguments().getLong(ARG_ITEM_ID);

            Log.d(TAG, "onCreate: movie id = " + movieId);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        if (movieId != -1) {
            Observable<Movie> movieOb = Observable.create(new Observable.OnSubscribe<Movie>() {
                @Override
                public void call(Subscriber<? super Movie> subscriber) {
                    try {
                        if (!subscriber.isUnsubscribed()) {
                            Movie movie = new Select().from(Movie.class).where(Movie_Table.tmdb_id.is(movieId)).querySingle();
                            subscriber.onNext(movie);
                            subscriber.onCompleted();
                        }
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
            });

            // Observable<Movie> movieObservable = movieService.getMovieObservableById(movieId);

            movieOb.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<Movie>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(Movie movie) {
                            ((TextView) rootView.findViewById(R.id.movie_detail)).setText(movie.overview);
                        }
                    });
        }

        return rootView;
    }
}
