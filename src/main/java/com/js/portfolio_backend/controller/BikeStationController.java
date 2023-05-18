package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.model.BikeStation;
import com.js.portfolio_backend.repository.BikeStationRepository;
import com.js.portfolio_backend.service.BikeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/stations")
public class BikeStationController {


    private BikeStationService bikeStationService;

    @Autowired
    public void BikeStationController(BikeStationService bikeStationService) {
        this.bikeStationService = bikeStationService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "http://localhost:5173")
    public List<BikeStation> getAll(){
        System.out.println("Get all called");
        List<BikeStation> bikeStations = bikeStationService.getAll();
        System.out.println(bikeStations);

        return bikeStations;
    }

    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            bikeStationService.importStationCSV(file);
            return ResponseEntity.ok("CSV file processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing CSV file.");
        }
    }
}
