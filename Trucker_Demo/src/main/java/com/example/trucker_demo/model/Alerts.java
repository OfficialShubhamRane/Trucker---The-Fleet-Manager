package com.example.trucker_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alerts {

    private String vin;
    private Character alertLevel;
    private Float fuelVolume;
    private Integer engineRpm;
    private Double latitude;
    private Double longitude;



}
