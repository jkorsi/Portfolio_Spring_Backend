package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.repository.BikeTripCollectionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bike-trips")
public class BikeTripController {

    //TODO: Implement rest of the CRUD (Create, retrieve, update, delete)(
    private final BikeTripCollectionRepository bikeRepo;

    public BikeTripController(BikeTripCollectionRepository bikeRepo){
        this.bikeRepo = bikeRepo;
    }
    @GetMapping("")
    public List<BikeTrip> getBikeTripList() {
        return bikeRepo.getBikeTripList();
    }

}
