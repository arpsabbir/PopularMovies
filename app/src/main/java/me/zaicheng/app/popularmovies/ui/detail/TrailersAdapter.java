package me.zaicheng.app.popularmovies.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.zaicheng.app.popularmovies.R;
import me.zaicheng.app.popularmovies.data.model.Trailer;
import me.zaicheng.app.popularmovies.di.ApplicationContext;
import me.zaicheng.app.popularmovies.utils.YoutubeUtil;

/**
 * Created by vmlinz on 4/10/16.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {
    List<Trailer> mTrailers;
    Context mContext;

    @Inject
    public TrailersAdapter(@ApplicationContext Context context) {
        this.mTrailers = new ArrayList<Trailer>();
        this.mContext = context;
    }

    public void setTrailers(List<Trailer> mTrailers) {
        this.mTrailers = mTrailers;
    }

    @Override
    public TrailersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_detail_trailer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailersAdapter.ViewHolder holder, int position) {
        holder.title.setText(mTrailers.get(position).name);
        if (mTrailers.get(position).id != null) {
            final String videoId = mTrailers.get(position).key;
            Glide.with(mContext)
                    .load(YoutubeUtil.getThumbNailUrl(videoId))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.thumbnail);
            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = YoutubeUtil.getYoutubePlayerIntent(videoId).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        } else {
            Glide.clear(holder.thumbnail);
            holder.thumbnail.setImageDrawable(null);
        }

    }

    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_movie_detail_trailer_title)
        public TextView title;
        @Bind(R.id.iv_movie_detail_thumbnail)
        public ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
