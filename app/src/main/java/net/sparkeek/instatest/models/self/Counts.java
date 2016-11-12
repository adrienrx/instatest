
package net.sparkeek.instatest.models.self;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Counts {

    @SerializedName("media")
    @Expose
    private Integer media;
    @SerializedName("followed_by")
    @Expose
    private Integer followedBy;
    @SerializedName("follows")
    @Expose
    private Integer follows;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Counts() {
    }

    /**
     * 
     * @param follows
     * @param followedBy
     * @param media
     */
    public Counts(Integer media, Integer followedBy, Integer follows) {
        this.media = media;
        this.followedBy = followedBy;
        this.follows = follows;
    }

    /**
     * 
     * @return
     *     The media
     */
    public Integer getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(Integer media) {
        this.media = media;
    }

    /**
     * 
     * @return
     *     The followedBy
     */
    public Integer getFollowedBy() {
        return followedBy;
    }

    /**
     * 
     * @param followedBy
     *     The followed_by
     */
    public void setFollowedBy(Integer followedBy) {
        this.followedBy = followedBy;
    }

    /**
     * 
     * @return
     *     The follows
     */
    public Integer getFollows() {
        return follows;
    }

    /**
     * 
     * @param follows
     *     The follows
     */
    public void setFollows(Integer follows) {
        this.follows = follows;
    }

}
