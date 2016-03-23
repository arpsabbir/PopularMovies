package me.zaicheng.app.popularmovies.ui.main;

import java.util.List;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.BusEvent;
import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.rxbus.RxBus;
import me.zaicheng.app.popularmovies.ui.base.Presenter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by vmlinz on 3/23/16.
 */
public class MoviesPresenter implements Presenter<MoviesMvpView> {

    private final DataManager mDataManager;
    private final RxBus mBus;
    private MoviesMvpView mMvpView;
    private Subscription mLoadMoviesSubscription;
    private Subscription mSyncMoviesSubscription;
    private CompositeSubscription mCompositeSubscription;

    @Inject
    public MoviesPresenter(DataManager dataManager, RxBus bus) {
        mDataManager = dataManager;
        mBus = bus;
    }

    @Override
    public void attachView(MoviesMvpView mvpView) {
        mMvpView = mvpView;
        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(mBus.toObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof BusEvent.MoviesSaved) {
                    loadMovies();
                }
            }
        }));
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (mLoadMoviesSubscription != null) {
            mLoadMoviesSubscription.unsubscribe();
        }

        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }

        if (mSyncMoviesSubscription != null) {
            mSyncMoviesSubscription.unsubscribe();
        }
    }

    public void syncMovies() {
        mSyncMoviesSubscription = mDataManager.syncMovies()
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
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Timber.d(movie.toString());
                    }
                });
    }

    public void loadMovies() {
        mLoadMoviesSubscription = mDataManager.getMoviesFromDb()
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
                });
    }
}
