
package net.sparkeek.instatest.models.recent;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Comments {

    @SerializedName("count")
    @Expose
    private Integer count;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Comments() {
    }

    /**
     * 
     * @param count
     */
    public Comments(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
