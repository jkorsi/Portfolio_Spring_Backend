package com.js.portfolio_backend.repository;

import com.js.portfolio_backend.datatransferobject.BikeTripDto;
import com.js.portfolio_backend.model.BikeTrip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.js.portfolio_backend.datatransferobject.BikeTripDto;

import java.time.LocalDateTime;

public interface BikeTripRepository extends JpaRepository<BikeTrip, Integer> {


    //IMPORTANT! When calling this "searchWithDate", the keyword should be passed in as "%"+keyword+"%"
    @Query("SELECT new com.js.portfolio_backend.datatransferobject.BikeTripDto(b.departureTime, b.returnTime ,bs1.stationName as departureStation, bs2.stationName as returnStation, b.distanceInMeters, b.distanceInKm, b.durationInSec, b.durationInMin) " +
            "FROM BikeTrip b " +
            "INNER JOIN BikeStation bs1 " +
            "ON b.deptStationId = bs1.id " +
            "INNER JOIN BikeStation bs2 " +
            "ON b.retStationId = bs2.id " +
            "WHERE (lower(bs1.stationName) LIKE :keyword " +
            "OR lower(bs2.stationAddress) LIKE :keyword " +
            "OR lower(bs2.stationName) LIKE :keyword " +
            "OR lower(bs2.stationCity) LIKE :keyword) " +
            "AND b.departureTime BETWEEN :minDate AND :maxDate")
    Page<BikeTripDto> searchWithDate(@Param("keyword") String keyword, @Param("minDate") LocalDateTime minDate, @Param("maxDate") LocalDateTime maxDate, Pageable pageable);

    //Search with only string columns
    @Query("SELECT b FROM BikeTrip b " +
            "INNER JOIN BikeStation bs " +
            "ON b.deptStationId = bs.id OR b.retStationId = bs.id " +
            "WHERE bs.stationCity LIKE %:keyword% OR " +
            "bs.stationName LIKE %:keyword% ")
    Page<BikeTrip> search(
            @Param("keyword") String keyword, Pageable pageable);

}
