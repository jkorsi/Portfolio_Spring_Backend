package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.model.BikeStation;
import com.js.portfolio_backend.service.BikeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import java.io.IOException;
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
        List<BikeStation> bikeStations = bikeStationService.getAll();
        return bikeStations;
    }

    @GetMapping("/sortBy/")
    public List<BikeStation> getByOrder(@RequestParam() String orderBy, @RequestParam(defaultValue = "asc") String orderDir){
        Sort.Direction direction = Sort.Direction.ASC;
        if (orderDir.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, orderBy);
        List<BikeStation> bikeStations = bikeStationService.getAll(sort);

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
