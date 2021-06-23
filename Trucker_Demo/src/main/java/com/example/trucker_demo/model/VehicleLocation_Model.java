package com.example.trucker_demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table
public class VehicleLocation_Model {

    @Id
    @NotNull
    private String locationID;
    private String latitude;
    private String longitude;
    private String locationName;

    /** Unique ID for every location */
    public VehicleLocation_Model(){
        locationID = UUID.randomUUID().toString();
    }

}
