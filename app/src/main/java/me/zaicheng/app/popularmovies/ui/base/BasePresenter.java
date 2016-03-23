package me.zaicheng.app.popularmovies.ui.base;

/**
 * Created by vmlinz on 3/23/16.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {
    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attacheView(MvpView) before"
                    + " requesting data to the Presenter");
        }
    }
}
