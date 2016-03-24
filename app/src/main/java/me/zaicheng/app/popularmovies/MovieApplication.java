package me.zaicheng.app.popularmovies;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.di.component.ApplicationComponent;
import me.zaicheng.app.popularmovies.di.component.DaggerApplicationComponent;
import me.zaicheng.app.popularmovies.di.module.ApplicationModule;
import me.zaicheng.app.popularmovies.rxbus.RxBus;
import timber.log.Timber;

/**
 * Created by vmlinz on 3/18/16.
 */
public class MovieApplication extends Application {
    @Inject
    RxBus mRxBus;
    @Inject
    DataManager mDataManager;
    ApplicationComponent mApplicationComponent;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        refWatcher = installLeakCanary(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);

        FlowManager.init(this);
    }

    // install leakcanary refwatcher
    private RefWatcher installLeakCanary(Application application) {
        if (BuildConfig.DEBUG) {
            return LeakCanary.install(application);
        } else {
            return RefWatcher.DISABLED;
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent mApplicationComponent) {
        this.mApplicationComponent = mApplicationComponent;
    }

    public static MovieApplication get(Context context) {
        return (MovieApplication) context.getApplicationContext();
    }

    public static RefWatcher getRefWatcher(Context context) {
        MovieApplication application = (MovieApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
