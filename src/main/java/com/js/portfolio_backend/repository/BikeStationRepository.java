package com.js.portfolio_backend.repository;
import com.js.portfolio_backend.model.BikeStation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BikeStationRepository extends PagingAndSortingRepository<BikeStation, Integer>, JpaRepository<BikeStation, Integer> {

    @Query("SELECT b FROM BikeStation b " +
            "WHERE lower(b.stationName) LIKE %:keyword% " +
            "OR lower(b.stationAddress) LIKE %:keyword% " +
            "OR lower(b.stationCity) LIKE %:keyword%")
    Page<BikeStation> search(@Param("keyword") String keyword, Pageable pageable);

}


