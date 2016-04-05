
package me.zaicheng.app.popularmovies.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Trailer {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("iso_639_1")
    @Expose
    public String iso6391;
    @SerializedName("key")
    @Expose
    public String key;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("site")
    @Expose
    public String site;
    @SerializedName("size")
    @Expose
    public long size;
    @SerializedName("type")
    @Expose
    public String type;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(iso6391).append(key).append(name).append(site).append(size).append(type).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Trailer) == false) {
            return false;
        }
        Trailer rhs = ((Trailer) other);
        return new EqualsBuilder().append(id, rhs.id).append(iso6391, rhs.iso6391).append(key, rhs.key).append(name, rhs.name).append(site, rhs.site).append(size, rhs.size).append(type, rhs.type).isEquals();
    }

}
