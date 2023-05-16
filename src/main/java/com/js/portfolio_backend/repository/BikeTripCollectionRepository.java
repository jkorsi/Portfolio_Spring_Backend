package com.js.portfolio_backend.repository;

import com.js.portfolio_backend.model.BikeTrip;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BikeTripCollectionRepository {
    private final ArrayList<BikeTrip> bikeTripList = new ArrayList<>();

    public BikeTripCollectionRepository(){

    }

    public ArrayList<BikeTrip> getBikeTripList() {

        //TODO: Pagination OR hard-limit for returned trip count
        return bikeTripList;
    }

    public ArrayList<BikeTrip> findTripsByStationName(String stationName) {

        //TODO: implement finding by station name
        //Integer bikeStationId = getBikeStationIdByName(stationName);
        //return bikeTripList.stream().filter(bikeTrip -> bikeTrip.retStationId().equals(id));
        return bikeTripList;
    }




}
