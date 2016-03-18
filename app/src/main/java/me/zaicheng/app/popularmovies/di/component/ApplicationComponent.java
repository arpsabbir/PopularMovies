package me.zaicheng.app.popularmovies.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.zaicheng.app.popularmovies.di.ApplicationContext;
import me.zaicheng.app.popularmovies.di.module.ApplicationModule;

/**
 * Created by vmlinz on 3/17/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ApplicationContext Context context();
    Application application();
}
