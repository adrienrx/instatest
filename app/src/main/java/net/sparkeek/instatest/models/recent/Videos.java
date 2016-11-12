
package net.sparkeek.instatest.models.recent;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Videos {

    @SerializedName("low_resolution")
    @Expose
    private LowResolution lowResolution;
    @SerializedName("standard_resolution")
    @Expose
    private StandardResolution standardResolution;
    @SerializedName("low_bandwidth")
    @Expose
    private LowBandwidth lowBandwidth;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Videos() {
    }

    /**
     * 
     * @param lowBandwidth
     * @param lowResolution
     * @param standardResolution
     */
    public Videos(LowResolution lowResolution, StandardResolution standardResolution, LowBandwidth lowBandwidth) {
        this.lowResolution = lowResolution;
        this.standardResolution = standardResolution;
        this.lowBandwidth = lowBandwidth;
    }

    /**
     * 
     * @return
     *     The lowResolution
     */
    public LowResolution getLowResolution() {
        return lowResolution;
    }

    /**
     * 
     * @param lowResolution
     *     The low_resolution
     */
    public void setLowResolution(LowResolution lowResolution) {
        this.lowResolution = lowResolution;
    }

    /**
     * 
     * @return
     *     The standardResolution
     */
    public StandardResolution getStandardResolution() {
        return standardResolution;
    }

    /**
     * 
     * @param standardResolution
     *     The standard_resolution
     */
    public void setStandardResolution(StandardResolution standardResolution) {
        this.standardResolution = standardResolution;
    }

    /**
     * 
     * @return
     *     The lowBandwidth
     */
    public LowBandwidth getLowBandwidth() {
        return lowBandwidth;
    }

    /**
     * 
     * @param lowBandwidth
     *     The low_bandwidth
     */
    public void setLowBandwidth(LowBandwidth lowBandwidth) {
        this.lowBandwidth = lowBandwidth;
    }

}
