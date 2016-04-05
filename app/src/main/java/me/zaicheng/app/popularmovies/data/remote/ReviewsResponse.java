
package me.zaicheng.app.popularmovies.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import me.zaicheng.app.popularmovies.data.model.Review;

@Generated("org.jsonschema2pojo")
public class ReviewsResponse {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("page")
    @Expose
    public long page;
    @SerializedName("results")
    @Expose
    public List<Review> reviews = new ArrayList<Review>();
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
        return new HashCodeBuilder().append(id).append(page).append(reviews).append(totalPages).append(totalResults).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ReviewsResponse) == false) {
            return false;
        }
        ReviewsResponse rhs = ((ReviewsResponse) other);
        return new EqualsBuilder().append(id, rhs.id).append(page, rhs.page).append(reviews, rhs.reviews).append(totalPages, rhs.totalPages).append(totalResults, rhs.totalResults).isEquals();
    }

}
