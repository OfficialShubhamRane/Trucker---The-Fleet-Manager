package com.example.trucker_demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class VehicleDetails_Model {

    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Integer redlineRPM;
    private Integer maxFuelVolumn;
    private String lastServiceDate;


}
