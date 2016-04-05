
package me.zaicheng.app.popularmovies.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import me.zaicheng.app.popularmovies.data.model.Genre;
import me.zaicheng.app.popularmovies.data.model.ProductionCompany;
import me.zaicheng.app.popularmovies.data.model.ProductionCountry;
import me.zaicheng.app.popularmovies.data.model.SpokenLanguage;

@Generated("org.jsonschema2pojo")
public class MovieResponse {

    @SerializedName("adult")
    @Expose
    public boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    public Object belongsToCollection;
    @SerializedName("budget")
    @Expose
    public long budget;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres = new ArrayList<Genre>();
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("imdb_id")
    @Expose
    public String imdbId;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("popularity")
    @Expose
    public double popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("production_companies")
    @Expose
    public List<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
    @SerializedName("production_countries")
    @Expose
    public List<ProductionCountry> productionCountries = new ArrayList<ProductionCountry>();
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("revenue")
    @Expose
    public long revenue;
    @SerializedName("runtime")
    @Expose
    public long runtime;
    @SerializedName("spoken_languages")
    @Expose
    public List<SpokenLanguage> spokenLanguages = new ArrayList<SpokenLanguage>();
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("video")
    @Expose
    public boolean video;
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
        return new HashCodeBuilder().append(adult).append(backdropPath).append(belongsToCollection).append(budget).append(genres).append(homepage).append(id).append(imdbId).append(originalLanguage).append(originalTitle).append(overview).append(popularity).append(posterPath).append(productionCompanies).append(productionCountries).append(releaseDate).append(revenue).append(runtime).append(spokenLanguages).append(status).append(tagline).append(title).append(video).append(voteAverage).append(voteCount).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieResponse) == false) {
            return false;
        }
        MovieResponse rhs = ((MovieResponse) other);
        return new EqualsBuilder().append(adult, rhs.adult).append(backdropPath, rhs.backdropPath).append(belongsToCollection, rhs.belongsToCollection).append(budget, rhs.budget).append(genres, rhs.genres).append(homepage, rhs.homepage).append(id, rhs.id).append(imdbId, rhs.imdbId).append(originalLanguage, rhs.originalLanguage).append(originalTitle, rhs.originalTitle).append(overview, rhs.overview).append(popularity, rhs.popularity).append(posterPath, rhs.posterPath).append(productionCompanies, rhs.productionCompanies).append(productionCountries, rhs.productionCountries).append(releaseDate, rhs.releaseDate).append(revenue, rhs.revenue).append(runtime, rhs.runtime).append(spokenLanguages, rhs.spokenLanguages).append(status, rhs.status).append(tagline, rhs.tagline).append(title, rhs.title).append(video, rhs.video).append(voteAverage, rhs.voteAverage).append(voteCount, rhs.voteCount).isEquals();
    }

}
