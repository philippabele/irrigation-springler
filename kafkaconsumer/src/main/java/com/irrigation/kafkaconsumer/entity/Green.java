package com.irrigation.kafkaconsumer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN")
public class Green {

    @Id
    @Column(name = "GREEN_ID")
    private long greenId;
    @Column(name = "PLACE")
    private String greenName;
    @Column(name = "WATER_DAY_MM")
    private Integer waterDay;

    public Green() {
    }

    public Green(long greenId, String greenName, Integer waterDay) {
        this.greenId = greenId;
        this.greenName = greenName;
        this.waterDay = waterDay;
    }

    public long getGreenId() {
        return greenId;
    }

    public void setGreenId(long greenId) {
        this.greenId = greenId;
    }

    public String getGreenName() {
        return greenName;
    }

    public void setGreenName(String greenName) {
        this.greenName = greenName;
    }

    public Integer getWaterDay() {
        return waterDay;
    }

    public void setWaterDay(Integer waterDay) {
        this.waterDay = waterDay;
    }

}
