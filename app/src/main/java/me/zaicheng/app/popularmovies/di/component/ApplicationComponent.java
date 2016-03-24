package me.zaicheng.app.popularmovies.di.component;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.RefWatcher;

import javax.inject.Singleton;

import dagger.Component;
import me.zaicheng.app.popularmovies.MovieApplication;
import me.zaicheng.app.popularmovies.data.DataManager;
import me.zaicheng.app.popularmovies.data.remote.MovieService;
import me.zaicheng.app.popularmovies.di.ApplicationContext;
import me.zaicheng.app.popularmovies.di.module.ApplicationModule;
import me.zaicheng.app.popularmovies.rxbus.RxBus;

/**
 * Created by vmlinz on 3/17/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MovieApplication movieApplication);

    @ApplicationContext Context context();
    Application application();
    MovieService movieService();
    DataManager dataManager();
    RxBus rxBus();
    RefWatcher refWatcher();
}
