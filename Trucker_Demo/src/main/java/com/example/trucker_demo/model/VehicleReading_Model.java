package com.example.trucker_demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table
public class VehicleReading_Model {

    @Id
    @NotNull
    private String readingUUID;
    private String vin;
    private Double latitude;
    private Double longitude;
    private Date timestamp;
    private Float fuelVolume;
    private Float speed;
    private Integer engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private Integer engineRpm;
    @Embedded
    private Tires_Model tires;

    public VehicleReading_Model(){
        this.readingUUID = UUID.randomUUID().toString();
    }

}
