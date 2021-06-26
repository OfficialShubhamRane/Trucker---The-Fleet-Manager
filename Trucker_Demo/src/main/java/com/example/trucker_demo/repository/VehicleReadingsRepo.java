package com.example.trucker_demo.repository;

import com.example.trucker_demo.model.VehicleReading_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleReadingsRepo extends JpaRepository<VehicleReading_Model, String> {


    @Query("SELECT r FROM VehicleReading_Model r WHERE r.vin = ?1 AND r.timestamp <= (CURRENT_TIMESTAMP - '+0 00:'+?2+':00.000000000') ORDER BY r.timestamp ASC")
    List<VehicleReading_Model> findAllSignalReadings(
            @Param("vin") String vin,
            @Param("minutes") String minutes)
            ;


    @Query("SELECT r FROM VehicleReading_Model r WHERE r.timestamp <= (CURRENT_TIMESTAMP - '+0 00:30:00.000000000') AND r.vin = ?1")
    List<VehicleReading_Model> findAllByVin(String vin)
            ;
}
