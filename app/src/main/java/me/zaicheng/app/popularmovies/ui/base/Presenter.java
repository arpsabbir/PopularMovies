package me.zaicheng.app.popularmovies.ui.base;

/**
 * Created by vmlinz on 3/23/16.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}
