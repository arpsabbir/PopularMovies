package me.zaicheng.app.popularmovies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.detail.DetailActivity;
import me.zaicheng.app.popularmovies.ui.detail.DetailFragment;
import me.zaicheng.app.popularmovies.utils.MovieUtil;

/**
 * Created by vmlinz on 3/23/16.
 */
public class MoviesAdapter
        extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private WeakReference<MoviesActivity> mActivity;
    private List<Movie> mMovies;

    @Inject
    public MoviesAdapter(MoviesActivity mActivity) {
        this.mActivity = new WeakReference<>(mActivity);
        mMovies = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mMovie = mMovies.get(position);

        if (holder.mMovie.posterPath != null) {
            Glide.with(mActivity.get())
                    .load(MovieUtil.getPosterImageUrl("w185", holder.mMovie.posterPath))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.mMoviePoster);
        } else {
            Glide.clear(holder.mMoviePoster);
            holder.mMoviePoster.setImageDrawable(null);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity.get().mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putLong(DetailFragment.ARG_ITEM_ID, holder.mMovie.tmdb_id);
                    Log.d(MoviesActivity.TAG, "onClick: id = " + holder.mMovie.tmdb_id);

                    DetailFragment fragment = new DetailFragment();
                    fragment.setArguments(arguments);
                    mActivity.get().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailFragment.ARG_ITEM_ID, holder.mMovie.tmdb_id);
                    Log.d(MoviesActivity.TAG, "onClick: id = " + holder.mMovie.tmdb_id);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @Bind(R.id.movie_poster)
        public ImageView mMoviePoster;
        public Movie mMovie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + mMovie.posterPath;
        }
    }
}
