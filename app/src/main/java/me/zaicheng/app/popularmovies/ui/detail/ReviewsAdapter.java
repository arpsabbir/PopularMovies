package me.zaicheng.app.popularmovies.ui.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Review;

/**
 * Created by vmlinz on 4/6/16.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<Review> mReviews;

    @Inject
    public ReviewsAdapter() {
        this.mReviews = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_detail_review_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mContent.setText(mReviews.get(position).content);
        holder.mAuthor.setText(mReviews.get(position).author);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public List<Review> getReviews() {
        return mReviews;
    }

    public void setReviews(List<Review> mReviews) {
        this.mReviews = mReviews;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_movie_detail_review_content)
        public TextView mContent;

        @Bind(R.id.tv_movie_detail_review_author)
        public TextView mAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
