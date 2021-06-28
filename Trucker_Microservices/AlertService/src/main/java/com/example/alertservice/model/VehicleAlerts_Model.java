package com.example.alertservice.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table
public class VehicleAlerts_Model {

    @Id
    @NotNull
    private String alertID;
    private String vin;
    private Date timestamp;
    private String rule;
    private String alertLevel;

    /** Generated unique ID for every alerts as VIN cannot be used unique key */
    public VehicleAlerts_Model(){
        this.alertID = UUID.randomUUID().toString();
    }


}
