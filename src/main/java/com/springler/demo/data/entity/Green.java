package com.springler.demo.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GREEN")
public class Green {
    @Id
    @Column(name = "GREEN_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long greenId;
    @Column(name = "NAME")
    private String greenName;
    @Column(name = "WATER_DAY_MM")
    private Integer waterDay;

    public long getGreenId() {
        return greenId;
    }

    public String getGreenName() {
        return greenName;
    }

    public Integer getWaterDay() {
        return waterDay;
    }
}
