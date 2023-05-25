package com.js.portfolio_backend.repository;

import com.js.portfolio_backend.model.BikeStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BikeStationRepository extends PagingAndSortingRepository<BikeStation, Integer>, JpaRepository<BikeStation, Integer> {
}
