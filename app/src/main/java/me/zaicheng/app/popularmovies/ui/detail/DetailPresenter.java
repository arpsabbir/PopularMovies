package me.zaicheng.app.popularmovies.ui.detail;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.rxbus.RxBus;
import me.zaicheng.app.popularmovies.ui.base.Presenter;
import rx.Subscriber;
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
        mSubscription.add(mBus.toObservable().subscribe());
    }

    @Override
    public void detachView() {
        mMvpView = null;
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    public void loadMovie(long id) {
        mSubscription.add(mDataManager.getMovieById(id)
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
                        mMvpView.get().showDetail(movie);
                    }
                }));
    }
}