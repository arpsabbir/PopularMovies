package me.zaicheng.app.popularmovies.ui.base;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.zaicheng.app.popularmovies.MovieApplication;
import me.zaicheng.app.popularmovies.di.component.ActivityComponent;
import me.zaicheng.app.popularmovies.di.component.DaggerActivityComponent;
import me.zaicheng.app.popularmovies.di.module.ActivityModule;

/**
 * Created by vmlinz on 3/23/16.
 */
public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(MovieApplication.get(this).getComponent())
                    .build();
        }

        return mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
