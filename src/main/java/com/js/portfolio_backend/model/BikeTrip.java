package com.js.portfolio_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "bike_trip", indexes = {
        @Index(name = "idx_bike_trip_deptstationid", columnList = "deptStationId"),
        @Index(name = "idx_bike_trip_retstationid", columnList = "retStationId"),
        @Index(name = "idx_bike_trip_departuretime", columnList = "departureTime"),
        @Index(name = "idx_bike_trip_deptstationid_departuretime", columnList = "deptStationId,departureTime"),
        @Index(name = "idx_bike_trip_retstationid_returntime", columnList = "retStationId,returnTime")
})

@Entity
public class BikeTrip {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_trip_seq")
    @SequenceGenerator(name = "bike_trip_seq", sequenceName = "bike_trip_seq", allocationSize = 1)
    private UUID tripId;
    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @Column(name = "returnTime")
    private LocalDateTime returnTime;

    @Column(name = "deptStationId")
    private Integer deptStationId;

    @Column(name = "retStationId")
    private Integer retStationId;

    @Column(name = "distanceInMeters")
    private Integer distanceInMeters;

    @Column(name = "distanceInKm")
    private Double distanceInKm;

    @Column(name = "durationInSec")
    private Integer durationInSec;

    @Column(name = "durationInMin")
    private Integer durationInMin;

    public BikeTrip(LocalDateTime departureTime, LocalDateTime returnTime, Integer deptStationId,
                    Integer retStationId, Integer distanceInMeters, Double distanceInKm, Integer durationInSec, Integer durationInMin) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.deptStationId = deptStationId;
        this.retStationId = retStationId;
        this.distanceInMeters = distanceInMeters;
        this.distanceInKm = distanceInKm;
        this.durationInSec = durationInSec;
        this.durationInMin = durationInMin;
    }

    public BikeTrip() {

    }

    public UUID getTripId() {
        return tripId;
    }

    public void setTripId(UUID tripId) {
        this.tripId = tripId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getDeptStationId() {
        return deptStationId;
    }

    public void setDeptStationId(Integer deptStationId) {
        this.deptStationId = deptStationId;
    }

    public Integer getRetStationId() {
        return retStationId;
    }

    public void setRetStationId(Integer retStationId) {
        this.retStationId = retStationId;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Integer distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getDurationInSec() {
        return durationInSec;
    }

    public void setDurationInSec(Integer durationInSec) {
        this.durationInSec = durationInSec;
    }

    public Double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public Integer getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(Integer durationInMinutes) {
        this.durationInMin = durationInMinutes;
    }

}
