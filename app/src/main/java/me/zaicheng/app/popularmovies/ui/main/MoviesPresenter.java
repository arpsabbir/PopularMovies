package me.zaicheng.app.popularmovies.ui.main;

import android.os.Bundle;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.BusEvent;
import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.data.local.PreferenceHelper;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.rxbus.RxBus;
import me.zaicheng.app.popularmovies.ui.base.Presenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by vmlinz on 3/23/16.
 */
public class MoviesPresenter implements Presenter<MoviesMvpView> {

    static final String MOVIES_KEY = "movies";

    private final DataManager mDataManager;
    private final RxBus mBus;
    private MoviesMvpView mMvpView;
    private CompositeSubscription mSubscription;
    private final PreferenceHelper mPreferenceHelper;

    @Inject
    public MoviesPresenter(DataManager dataManager, RxBus bus, PreferenceHelper preferenceHelper) {
        mDataManager = dataManager;
        mBus = bus;
        mPreferenceHelper = preferenceHelper;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(MoviesMvpView mvpView) {
        mMvpView = mvpView;
        mSubscription.add(mBus.toObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof BusEvent.MoviesSaved) {
                    loadMovies();
                } else if (event instanceof BusEvent.FavoriteMoviesSynced) {
                    loadFavoriteMovies();
                }
            }
        }));

        mSubscription.add(mPreferenceHelper.getRxSharedPreference().getString(PreferenceHelper.PREF_KEY_MOVIE_ORDER).asObservable().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Timber.d("Movie Order Preference Changed, resync movies database");
                syncMovies();
            }
        }));
    }

    private void loadFavoriteMovies() {
        mSubscription.add(Observable.just(mPreferenceHelper.getMoviesFromFavorites())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movies loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error loading the movies");
                        mMvpView.showError();
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if (!movies.isEmpty()) {
                            mMvpView.showMovies(movies);
                        } else {
                            mMvpView.showMoviesEmpty();
                        }
                    }
                }));
    }

    @Override
    public void detachView() {
        mMvpView = null;

        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void syncMovies() {
        mSubscription.add(mDataManager.syncMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movies synced");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Movies syning error");
                        mMvpView.showMoviesEmpty();
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Timber.d(movie.toString());
                    }
                }));
    }

    public void loadMovies() {
        mSubscription.add(mDataManager.getMoviesFromDb()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movies loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Error loading the movies");
                        mMvpView.showError();
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if (!movies.isEmpty()) {
                            mMvpView.showMovies(movies);
                        } else {
                            mMvpView.showMoviesEmpty();
                        }
                    }
                }));
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        List<Movie> movies = Parcels.unwrap(savedInstanceState.getParcelable("movies"));
        if ((movies != null) && !movies.isEmpty()) {
            mMvpView.showMovies(movies);
        }
    }

    public void saveInstanceState(Bundle outState, List<Movie> movies) {
        outState.putParcelable("movies", Parcels.wrap(movies));
    }
}
