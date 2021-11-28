package com.irrigation.kafkaproducer.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@NonNull
public class City {

    private String name;
    private Double lat;
    private Double lon;

}
