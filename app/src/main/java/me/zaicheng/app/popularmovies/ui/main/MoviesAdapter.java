package me.zaicheng.app.popularmovies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Movie;
import me.zaicheng.app.popularmovies.ui.detail.DetailActivity;
import me.zaicheng.app.popularmovies.ui.detail.DetailFragment;

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
        holder.mItem = mMovies.get(position);
        holder.mIdView.setText(mMovies.get(position).title);
        holder.mContentView.setText(mMovies.get(position).posterPath);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity.get().mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putLong(DetailFragment.ARG_ITEM_ID, holder.mItem.tmdb_id);
                    Log.d(MoviesActivity.TAG, "onClick: id = " + holder.mItem.tmdb_id);

                    DetailFragment fragment = new DetailFragment();
                    fragment.setArguments(arguments);
                    mActivity.get().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailFragment.ARG_ITEM_ID, holder.mItem.tmdb_id);
                    Log.d(MoviesActivity.TAG, "onClick: id = " + holder.mItem.tmdb_id);

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
