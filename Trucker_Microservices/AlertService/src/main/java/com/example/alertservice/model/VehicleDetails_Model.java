package com.example.alertservice.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

public class VehicleDetails_Model {

    @Id
    @NotNull
    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Integer redlineRpm;
    private Integer maxFuelVolume;
    private Date lastServiceDate;


}
