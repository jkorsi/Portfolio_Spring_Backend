package com.js.portfolio_backend.repository;

import com.js.portfolio_backend.model.BikeTrip;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BikeTripCollectionRepository {
    private List<BikeTrip> bikeTripList;

    public BikeTripCollectionRepository(){
    }

    public List<BikeTrip> getBikeTripList() {

        //TODO: Pagination OR hard-limit for returned trip count
        return bikeTripList;
    }

    public List<BikeTrip> findTripsByStationName(String stationName) {

        //TODO: implement finding by station name something like below
        //Integer bikeStationId = getBikeStationIdByName(stationName);
        //return bikeTripList.stream().filter(bikeTrip -> bikeTrip.retStationId().equals(id));
        return bikeTripList;
    }




}
