package com.js.portfolio_backend.datatransferobject;

import java.time.LocalDateTime;

public class BikeTripDto {
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private String departureStation;
    private String returnStation;
    private Integer distanceInMeters;

    private Double distanceInKm;
    private Integer durationInSec;

    private Integer durationInMin;

    public BikeTripDto(LocalDateTime departureTime, LocalDateTime returnTime, String departureStation,
                       String returnStation, Integer distanceInMeters, Double distanceInKm,  Integer durationInSec, Integer durationInMin) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStation = departureStation;
        this.returnStation = returnStation;
        this.distanceInMeters = distanceInMeters;
        this.distanceInKm = distanceInKm;
        this.durationInSec = durationInSec;
        this.durationInMin = durationInMin;
    }

    public BikeTripDto() {
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
    public Double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public Integer getDurationInMin() {
        return durationInMin;
    }

    public void setDurationInMin(Integer durationInMin) {
        this.durationInMin = durationInMin;
    }
}
