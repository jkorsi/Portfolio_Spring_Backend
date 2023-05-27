package com.js.portfolio_backend.datatransferobject;

import java.time.LocalDateTime;

public class BikeTripDto {
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private String departureStation;
    private String returnStation;
    private Integer distanceInMeters;
    private Integer durationInSec;

    private Integer distanceInKm;
    private Integer durationInMinutes;

    public BikeTripDto(LocalDateTime departureTime, LocalDateTime returnTime, String departureStation,
                       String returnStation, Integer distanceInMeters, Integer durationInSec) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.distanceInMeters = distanceInMeters;
        this.distanceInKm = distanceInMeters / 1000;
        this.durationInSec = durationInSec;
        this.durationInMinutes = durationInSec / 60;
    }

    public BikeTripDto() {
    }

    public double getDistanceInKm() {
        return distanceInMeters / 1000.0;
    }

    public double getDurationInMinutes() {
        return durationInSec / 60.0;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
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

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(String returnStation) {
        this.returnStation = returnStation;
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
}
