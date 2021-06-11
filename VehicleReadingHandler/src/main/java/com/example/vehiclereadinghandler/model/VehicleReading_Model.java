package com.example.vehiclereadinghandler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleReading_Model {

    private String vin;
    private Double latitude;
    private Double longitude;
    private String timestamp;
    private float fuelVolumn;
    private float speed;
    private int engineHP;
    private boolean checkEnginLightsOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRPM;
    private Tires_Model tires;


}
