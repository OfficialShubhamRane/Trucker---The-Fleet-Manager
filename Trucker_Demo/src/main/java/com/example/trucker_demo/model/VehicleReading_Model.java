package com.example.trucker_demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
    private Date timestamp;
    private float fuelVolumn;
    private float speed;
    private int engineHP;
    private boolean checkEnginLightsOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRPM;
    @Embedded
    private Tires_Model tires;


}
