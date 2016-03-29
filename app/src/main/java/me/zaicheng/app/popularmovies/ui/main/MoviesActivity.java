package me.zaicheng.app.popularmovies.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.local.PreferenceHelper;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.base.BaseActivity;
import me.zaicheng.app.popularmovies.ui.detail.DetailActivity;
import timber.log.Timber;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MoviesActivity extends BaseActivity implements MoviesMvpView {

    static final String TAG = MoviesActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    boolean mTwoPane;

    @Inject
    MoviesPresenter mMoviesPresenter;
    MoviesAdapter mMoviesAdapter = new MoviesAdapter(this);

    @Inject
    PreferenceHelper mPreferenceHelper;

    @Bind(R.id.movie_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.movie_detail_container)
    @Nullable
    View mContainer;
    @Bind(R.id.spinner)
    AppCompatSpinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setupSpinner();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        assert mRecyclerView != null;

        if (mContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        GridLayoutManager glm;
        if (mTwoPane) {
            glm = new GridLayoutManager(this, 1);
        } else {
            glm = new GridLayoutManager(this, 2);
        }
        mRecyclerView.setAdapter(mMoviesAdapter);
        mRecyclerView.setLayoutManager(glm);
        mMoviesPresenter.attachView(this);
        if (savedInstanceState == null || !savedInstanceState.containsKey(MoviesPresenter.MOVIES_KEY)) {
            mMoviesPresenter.syncMovies();
        } else {
            mMoviesPresenter.restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mMoviesPresenter.saveInstanceState(outState, mMoviesAdapter.getMovies());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoviesPresenter.detachView();
        Timber.d("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupSpinner() {
        String[] options = new String[]{
                "Most Popular Movies",
                "Top Rated Movies",
                "Favorite Movies"
        };

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getSupportActionBar().getThemedContext(), android.R.layout.simple_spinner_dropdown_item, options);
        mSpinner.setAdapter(arrayAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Timber.d("Spinner Popup menu item selected: i = %d, l = %d", i, l);
                switch (i) {
                    case 0:
                        mPreferenceHelper.getSharedPreference()
                                .edit()
                                .putString(PreferenceHelper.PREF_KEY_MOVIE_ORDER,
                                        PreferenceHelper.PREF_VALUE_MOVIE_ORDER_POPULAR).apply();
                        break;
                    case 1:
                        mPreferenceHelper.getSharedPreference()
                                .edit()
                                .putString(PreferenceHelper.PREF_KEY_MOVIE_ORDER,
                                        PreferenceHelper.PREF_VALUE_MOVIE_ORDER_TOP_RATED).apply();
                        break;
                    case 2:
                        mPreferenceHelper.getSharedPreference()
                                .edit()
                                .putString(PreferenceHelper.PREF_KEY_MOVIE_ORDER,
                                        PreferenceHelper.PREF_VALUE_MOVIE_ORDER_FAVORITE).apply();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // MVP view callbacks
    @Override
    public void showMovies(List<Movie> movies) {
        mMoviesAdapter.setMovies(movies);
        mMoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Timber.e("Movie List Error");
    }

    @Override
    public void showMoviesEmpty() {
        mMoviesAdapter.setMovies(Collections.<Movie>emptyList());
        mMoviesAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_movies, Toast.LENGTH_LONG).show();
    }
}
