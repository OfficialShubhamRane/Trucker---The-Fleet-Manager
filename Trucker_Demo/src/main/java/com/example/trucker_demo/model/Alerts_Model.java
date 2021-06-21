package com.example.trucker_demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table
public class Alerts_Model {

    @Id
    @NotNull
    private String alertID;
    private String vin;
    private Date timestamp;
    private String rule;
    private String alertLevel;

    /** Generated unique ID for every alerts as VIN cannot be used unique key */
    public  Alerts_Model(){
        this.alertID = UUID.randomUUID().toString();
    }


}
