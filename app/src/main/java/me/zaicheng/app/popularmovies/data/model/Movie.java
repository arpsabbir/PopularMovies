package me.zaicheng.app.popularmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import me.zaicheng.app.popularmovies.data.local.MovieDatabase;

@Parcel(analyze = {Movie.class})
@Table(database = MovieDatabase.class, name = "movies")
@Generated("org.jsonschema2pojo")
public class Movie extends BaseModel {
    public Movie() {
    }

    @SerializedName("adult")
    @Expose
    public boolean adult;
    @Column(name = "backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("genre_ids")
    @Expose
    public List<Long> genreIds = new ArrayList<Long>();
    @PrimaryKey
    @Column(name = "tmdb_id")
    @SerializedName("id")
    @Expose
    public long tmdb_id;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @Column
    @SerializedName("overview")
    @Expose
    public String overview;
    @Column(name = "release_date")
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @Column(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @Column(name = "popularity")
    @SerializedName("popularity")
    @Expose
    public double popularity;
    @Column
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("video")
    @Expose
    public boolean video;
    @Column(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;
    @SerializedName("vote_count")
    @Expose
    public long voteCount;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(adult).append(backdropPath).append(genreIds).append(tmdb_id).append(originalLanguage).append(originalTitle).append(overview).append(releaseDate).append(posterPath).append(popularity).append(title).append(video).append(voteAverage).append(voteCount).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Movie) == false) {
            return false;
        }
        Movie rhs = ((Movie) other);
        return new EqualsBuilder().append(adult, rhs.adult).append(backdropPath, rhs.backdropPath).append(genreIds, rhs.genreIds).append(tmdb_id, rhs.tmdb_id).append(originalLanguage, rhs.originalLanguage).append(originalTitle, rhs.originalTitle).append(overview, rhs.overview).append(releaseDate, rhs.releaseDate).append(posterPath, rhs.posterPath).append(popularity, rhs.popularity).append(title, rhs.title).append(video, rhs.video).append(voteAverage, rhs.voteAverage).append(voteCount, rhs.voteCount).isEquals();
    }

}