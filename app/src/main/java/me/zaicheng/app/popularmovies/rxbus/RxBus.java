package me.zaicheng.app.popularmovies.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by vmlinz on 3/23/16.
 */

public class RxBus {

    private static volatile RxBus mDefaultInstance;

    private RxBus() {
    }

    // default singleton instance
    public static RxBus getDefault() {
        if (mDefaultInstance == null) {
            synchronized (RxBus.class) {
                if (mDefaultInstance == null) {
                    mDefaultInstance = new RxBus();
                }
            }
        }
        return mDefaultInstance;
    }

    // create a new instance
    public static RxBus newInstance() {
        return new RxBus();
    }

    private final Subject<Object, Object> mBus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        mBus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return mBus;
    }
}
