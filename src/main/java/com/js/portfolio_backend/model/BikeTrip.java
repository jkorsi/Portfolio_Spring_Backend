package com.js.portfolio_backend.model;
import java.time.LocalDateTime;

//TODO: Create @Entity ??
public record BikeTrip(
        Integer tripId,
    LocalDateTime departureTime,
    LocalDateTime returnTime,
    Integer deptStationId,
    Integer retStationId,
    Integer distanceInMeters,
    Integer durationInSec
){

}
