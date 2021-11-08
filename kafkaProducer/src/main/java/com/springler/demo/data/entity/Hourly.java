
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
@JsonPropertyOrder({ "dt", "temp", "feels_like", "pressure", "humidity", "dew_point", "uvi", "clouds", "visibility",
        "wind_speed", "wind_deg", "wind_gust", "weather", "rain" })
@Generated("jsonschema2pojo")
public class Hourly {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("dew_point")
    private Double dewPoint;
    @JsonProperty("uvi")
    private Integer uvi;
    @JsonProperty("clouds")
    private Integer clouds;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind_speed")
    private Double windSpeed;
    @JsonProperty("wind_deg")
    private Integer windDeg;
    @JsonProperty("wind_gust")
    private Double windGust;
    @JsonProperty("weather")
    private List<Weather__1> weather = null;
    @JsonProperty("rain")
    private Rain rain;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hourly() {
    }

    /**
     * 
     * @param rain
     * @param temp
     * @param visibility
     * @param windGust
     * @param uvi
     * @param pressure
     * @param clouds
     * @param dewPoint
     * @param dt
     * @param feelsLike
     * @param windDeg
     * @param weather
     * @param humidity
     * @param windSpeed
     */
    public Hourly(Integer dt, Double temp, Double feelsLike, Integer pressure, Integer humidity, Double dewPoint,
            Integer uvi, Integer clouds, Integer visibility, Double windSpeed, Integer windDeg, Double windGust,
            List<Weather__1> weather, Rain rain) {
        super();
        this.dt = dt;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dewPoint = dewPoint;
        this.uvi = uvi;
        this.clouds = clouds;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.windGust = windGust;
        this.weather = weather;
        this.rain = rain;
    }

    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Hourly withDt(Integer dt) {
        this.dt = dt;
        return this;
    }

    @JsonProperty("temp")
    public Double getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Hourly withTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    @JsonProperty("feels_like")
    public Double getFeelsLike() {
        return feelsLike;
    }

    @JsonProperty("feels_like")
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Hourly withFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
        return this;
    }

    @JsonProperty("pressure")
    public Integer getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Hourly withPressure(Integer pressure) {
        this.pressure = pressure;
        return this;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Hourly withHumidity(Integer humidity) {
        this.humidity = humidity;
        return this;
    }

    @JsonProperty("dew_point")
    public Double getDewPoint() {
        return dewPoint;
    }

    @JsonProperty("dew_point")
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Hourly withDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
        return this;
    }

    @JsonProperty("uvi")
    public Integer getUvi() {
        return uvi;
    }

    @JsonProperty("uvi")
    public void setUvi(Integer uvi) {
        this.uvi = uvi;
    }

    public Hourly withUvi(Integer uvi) {
        this.uvi = uvi;
        return this;
    }

    @JsonProperty("clouds")
    public Integer getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Hourly withClouds(Integer clouds) {
        this.clouds = clouds;
        return this;
    }

    @JsonProperty("visibility")
    public Integer getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Hourly withVisibility(Integer visibility) {
        this.visibility = visibility;
        return this;
    }

    @JsonProperty("wind_speed")
    public Double getWindSpeed() {
        return windSpeed;
    }

    @JsonProperty("wind_speed")
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Hourly withWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    @JsonProperty("wind_deg")
    public Integer getWindDeg() {
        return windDeg;
    }

    @JsonProperty("wind_deg")
    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Hourly withWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
        return this;
    }

    @JsonProperty("wind_gust")
    public Double getWindGust() {
        return windGust;
    }

    @JsonProperty("wind_gust")
    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Hourly withWindGust(Double windGust) {
        this.windGust = windGust;
        return this;
    }

    @JsonProperty("weather")
    public List<Weather__1> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(List<Weather__1> weather) {
        this.weather = weather;
    }

    public Hourly withWeather(List<Weather__1> weather) {
        this.weather = weather;
        return this;
    }

    @JsonProperty("rain")
    public Rain getRain() {
        return rain;
    }

    @JsonProperty("rain")
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Hourly withRain(Rain rain) {
        this.rain = rain;
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

    public Hourly withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Hourly.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("dt");
        sb.append('=');
        sb.append(((this.dt == null) ? "<null>" : this.dt));
        sb.append(',');
        sb.append("temp");
        sb.append('=');
        sb.append(((this.temp == null) ? "<null>" : this.temp));
        sb.append(',');
        sb.append("feelsLike");
        sb.append('=');
        sb.append(((this.feelsLike == null) ? "<null>" : this.feelsLike));
        sb.append(',');
        sb.append("pressure");
        sb.append('=');
        sb.append(((this.pressure == null) ? "<null>" : this.pressure));
        sb.append(',');
        sb.append("humidity");
        sb.append('=');
        sb.append(((this.humidity == null) ? "<null>" : this.humidity));
        sb.append(',');
        sb.append("dewPoint");
        sb.append('=');
        sb.append(((this.dewPoint == null) ? "<null>" : this.dewPoint));
        sb.append(',');
        sb.append("uvi");
        sb.append('=');
        sb.append(((this.uvi == null) ? "<null>" : this.uvi));
        sb.append(',');
        sb.append("clouds");
        sb.append('=');
        sb.append(((this.clouds == null) ? "<null>" : this.clouds));
        sb.append(',');
        sb.append("visibility");
        sb.append('=');
        sb.append(((this.visibility == null) ? "<null>" : this.visibility));
        sb.append(',');
        sb.append("windSpeed");
        sb.append('=');
        sb.append(((this.windSpeed == null) ? "<null>" : this.windSpeed));
        sb.append(',');
        sb.append("windDeg");
        sb.append('=');
        sb.append(((this.windDeg == null) ? "<null>" : this.windDeg));
        sb.append(',');
        sb.append("windGust");
        sb.append('=');
        sb.append(((this.windGust == null) ? "<null>" : this.windGust));
        sb.append(',');
        sb.append("weather");
        sb.append('=');
        sb.append(((this.weather == null) ? "<null>" : this.weather));
        sb.append(',');
        sb.append("rain");
        sb.append('=');
        sb.append(((this.rain == null) ? "<null>" : this.rain));
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
