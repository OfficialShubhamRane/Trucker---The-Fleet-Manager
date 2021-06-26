package com.example.trucker_demo.repository;

import com.example.trucker_demo.model.Alerts_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertsRepo extends JpaRepository<Alerts_Model, String> {

    /** returns vehicle specific alerts */
    List<Alerts_Model> findAllByVin(String vin);

    /** returns number of HIGH alerts within last 2 hours for all the vehicles */
    @Query("SELECT a FROM Alerts_Model a WHERE a.timestamp <= (CURRENT_TIMESTAMP - '+0 02:00:00.000000000') AND a.alertLevel = 'HIGH'")
    List<Alerts_Model> findAllByTimestamp();

    @Query("SELECT a FROM Alerts_Model a WHERE a.timestamp <= (CURRENT_TIMESTAMP - '+0 02:00:00.000000000') AND a.alertLevel = 'HIGH' ORDER BY a.timestamp ASC")
    List<Alerts_Model> findAllByASCTimestamp();

    @Query("SELECT a FROM Alerts_Model a WHERE a.timestamp <= (CURRENT_TIMESTAMP - '+0 02:00:00.000000000') AND a.alertLevel = 'HIGH' ORDER BY a.timestamp DESC")
    List<Alerts_Model> findAllByDSCTimestamp();
}
