package com.example.alertservice.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String speed;
    private Integer engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private Integer engineRpm;
    @Embedded
    private Tires_Model tires;

    /** To store every reading and keep it unique */
    public VehicleReading_Model(){
        this.readingUUID = UUID.randomUUID().toString();
    }

}
