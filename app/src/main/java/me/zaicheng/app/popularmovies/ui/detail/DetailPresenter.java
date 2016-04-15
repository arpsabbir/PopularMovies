package me.zaicheng.app.popularmovies.ui.detail;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.BusEvent;
import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.data.model.Review;
import me.zaicheng.app.popularmovies.data.model.Trailer;
import me.zaicheng.app.popularmovies.data.remote.MovieResponse;
import me.zaicheng.app.popularmovies.rxbus.RxBus;
import me.zaicheng.app.popularmovies.ui.base.Presenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by vmlinz on 3/24/16.
 */
public class DetailPresenter implements Presenter<DetailMvpView> {
    private final DataManager mDataManager;
    private final RxBus mBus;
    private WeakReference<DetailMvpView> mMvpView;
    private CompositeSubscription mSubscription;

    @Inject
    public DetailPresenter(DataManager dataManager, RxBus bus) {
        mDataManager = dataManager;
        mBus = bus;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public void attachView(DetailMvpView mvpView) {
        mMvpView = new WeakReference<>(mvpView);
        mSubscription.add(mBus.toObservable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof BusEvent.FavoriteMovieClicked) {
                    Timber.d("Favorite Movie Clicked");
                    favorCurrentMovie();
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

    public void favorCurrentMovie() {
        mSubscription.add(mDataManager.getMovieById(mMvpView.get().getMovieId())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Current movie loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to load current movie");

                    }

                    @Override
                    public void onNext(Movie movie) {
                        Timber.d("Add movie to favorite");
                        mDataManager.addMovieToFavorites(movie);
                    }
                }));
    }

    public void loadMovie(long id) {
        mSubscription.add(mDataManager.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movie detail loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to load movie detail");
                        mMvpView.get().showError();
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Timber.d("Showing movie detail");
                        mMvpView.get().showMovie(movie);
                    }
                }));
    }

    public void loadTrailers(long id) {
        mSubscription.add(mDataManager.getMovieTrailersById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Trailer>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movie trailers loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to load movie trailers");
                    }

                    @Override
                    public void onNext(List<Trailer> trailers) {
                        Timber.d(trailers.toString());
                        mMvpView.get().showTrailers(trailers);
                    }
                }));
    }

    public void loadReviews(long id) {
        mSubscription.add(mDataManager.getMovieReviewsById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Review>>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movie reviews loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("Failed to load movie reviews");
                    }

                    @Override
                    public void onNext(List<Review> reviews) {
                        Timber.d(reviews.toString());
                        mMvpView.get().showReviews(reviews);
                    }
                }));
    }

    public void loadDetails(long id) {
        mSubscription.add(mDataManager.getMovieDetailsById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("Movie details loaded");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Failed to load movie details");
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        Timber.d(movieResponse.toString());
                        mMvpView.get().showDetails(movieResponse);
                    }
                }));
    }
}
