package me.zaicheng.app.popularmovies.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import me.zaicheng.app.popularmovies.di.ApplicationContext;

/**
 * Created by vmlinz on 3/17/16.
 */

@Module
public class ApplicationModule {
    protected final Application mApplication;


    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }
}
