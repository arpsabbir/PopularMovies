package me.zaicheng.app.popularmovies.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import me.zaicheng.app.popularmovies.data.model.Movie;

@Generated("org.jsonschema2pojo")
public class MoviesResponse {

    @SerializedName("page")
    @Expose
    public long page;
    @SerializedName("results")
    @Expose
    public List<Movie> results = new ArrayList<Movie>();
    @SerializedName("total_pages")
    @Expose
    public long totalPages;
    @SerializedName("total_results")
    @Expose
    public long totalResults;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(page).append(results).append(totalPages).append(totalResults).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MoviesResponse) == false) {
            return false;
        }
        MoviesResponse rhs = ((MoviesResponse) other);
        return new EqualsBuilder().append(page, rhs.page).append(results, rhs.results).append(totalPages, rhs.totalPages).append(totalResults, rhs.totalResults).isEquals();
    }

}