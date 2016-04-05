
package me.zaicheng.app.popularmovies.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import me.zaicheng.app.popularmovies.data.model.Trailer;

@Generated("org.jsonschema2pojo")
public class TrailersResponse {

    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("results")
    @Expose
    public List<Trailer> trailers = new ArrayList<Trailer>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(trailers).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrailersResponse) == false) {
            return false;
        }
        TrailersResponse rhs = ((TrailersResponse) other);
        return new EqualsBuilder().append(id, rhs.id).append(trailers, rhs.trailers).isEquals();
    }

}
