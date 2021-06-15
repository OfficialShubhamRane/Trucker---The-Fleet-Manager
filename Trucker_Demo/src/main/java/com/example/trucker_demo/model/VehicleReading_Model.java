package com.example.trucker_demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class VehicleReading_Model {

    @Id
    @NotNull
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
//    private Tires_Model tires;


}
