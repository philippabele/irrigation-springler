
package com.springler.demo.data.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "lat", "lon", "timezone", "timezone_offset", "current", "hourly" })
@Generated("jsonschema2pojo")
public class History {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezone_offset")
    private Integer timezoneOffset;
    @JsonProperty("current")
    private Current current;
    @JsonProperty("hourly")
    private List<Hourly> hourly = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public History() {
    }

    /**
     * 
     * @param current
     * @param timezoneOffset
     * @param timezone
     * @param lon
     * @param hourly
     * @param lat
     */
    public History(Double lat, Double lon, String timezone, Integer timezoneOffset, Current current,
            List<Hourly> hourly) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezoneOffset = timezoneOffset;
        this.current = current;
        this.hourly = hourly;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public History withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    public History withLon(Double lon) {
        this.lon = lon;
        return this;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public History withTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    @JsonProperty("timezone_offset")
    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    @JsonProperty("timezone_offset")
    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public History withTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
        return this;
    }

    @JsonProperty("current")
    public Current getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(Current current) {
        this.current = current;
    }

    public History withCurrent(Current current) {
        this.current = current;
        return this;
    }

    @JsonProperty("hourly")
    public List<Hourly> getHourly() {
        return hourly;
    }

    @JsonProperty("hourly")
    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    public History withHourly(List<Hourly> hourly) {
        this.hourly = hourly;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public History withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(History.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("lat");
        sb.append('=');
        sb.append(((this.lat == null) ? "<null>" : this.lat));
        sb.append(',');
        sb.append("lon");
        sb.append('=');
        sb.append(((this.lon == null) ? "<null>" : this.lon));
        sb.append(',');
        sb.append("timezone");
        sb.append('=');
        sb.append(((this.timezone == null) ? "<null>" : this.timezone));
        sb.append(',');
        sb.append("timezoneOffset");
        sb.append('=');
        sb.append(((this.timezoneOffset == null) ? "<null>" : this.timezoneOffset));
        sb.append(',');
        sb.append("current");
        sb.append('=');
        sb.append(((this.current == null) ? "<null>" : this.current));
        sb.append(',');
        sb.append("hourly");
        sb.append('=');
        sb.append(((this.hourly == null) ? "<null>" : this.hourly));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
