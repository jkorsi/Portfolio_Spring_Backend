package com.js.portfolio_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.portfolio_backend.model.BikeStation;
import com.js.portfolio_backend.service.BikeStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/stations")
public class BikeStationController {
    private ObjectMapper objectMapper;
    private BikeStationService bikeStationService;

    @Autowired
    public void BikeStationController(BikeStationService bikeStationService, ObjectMapper objectMapper) {
        this.bikeStationService = bikeStationService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:5173")
    public Page<BikeStation> getByOrder(
            @RequestParam(defaultValue = "stationName") String orderBy,
            @RequestParam(defaultValue = "asc") String orderDir,
            @PageableDefault(size = 10) Pageable pageable) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (orderDir.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        Sort sort = Sort.by(direction, orderBy);
        Page<BikeStation> bikeStationPage = bikeStationService.getAll(sort, pageable);

        return bikeStationPage;
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
