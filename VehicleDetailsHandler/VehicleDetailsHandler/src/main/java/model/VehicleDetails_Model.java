package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDetails_Model {

    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Integer redlineRPM;
    private Integer maxFuelVolumn;
    private String lastServiceDate;


}
