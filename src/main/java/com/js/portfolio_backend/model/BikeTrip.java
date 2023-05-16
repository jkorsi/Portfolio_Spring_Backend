package com.js.portfolio_backend.model;
import java.time.LocalDateTime;

public record BikeTrip(
        Integer tripId,
    LocalDateTime departureTime,
    LocalDateTime returnTime,
    Integer deptStationId,
    Integer retStationId,
    Integer distanceInM,
    Integer durationInSec
){

}
