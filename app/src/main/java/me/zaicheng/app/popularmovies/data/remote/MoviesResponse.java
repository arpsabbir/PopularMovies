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
    private long page;
    @SerializedName("results")
    @Expose
    private List<Movie> results = new ArrayList<>();
    @SerializedName("total_pages")
    @Expose
    private long totalPages;
    @SerializedName("total_results")
    @Expose
    private long totalResults;

    /**
     * @return The page
     */
    public long getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(long page) {
        this.page = page;
    }

    /**
     * @return The results
     */
    public List<Movie> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Movie> results) {
        this.results = results;
    }

    /**
     * @return The totalPages
     */
    public long getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalResults
     */
    public long getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

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
