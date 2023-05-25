package com.js.portfolio_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BikeTrip{

    @Id @GeneratedValue
    private Integer tripId;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private Integer deptStationId;
    private Integer retStationId;
    private  Integer distanceInMeters;
    private Integer durationInSec;
}
