package com.springler.demo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NonNull
public class PrecipitationDataPoint {

    private Integer dt;
    private Double mm;

    public PrecipitationDataPoint(Hourly hourly) {
        this.dt = hourly.getDt();
        this.mm = hourly.getRain().get_1h();
    }

}
