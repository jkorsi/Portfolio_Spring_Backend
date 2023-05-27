package com.js.portfolio_backend.controller;

import com.js.portfolio_backend.datatransferobject.BikeTripDto;
import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.service.BikeTripService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/biketrips")
public class BikeTripController {

    //TODO: Implement rest of the CRUD (Create, retrieve, update, delete)(
    private final BikeTripService tripService;

    public BikeTripController(BikeTripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("")
    public List<BikeTrip> getBikeTripList() {
        return tripService.getAll();
    }

    //TODO: implement like this
    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:5173")
    public Page<BikeTripDto> searchByKeyword(@RequestParam("keyword") String keyword,
                                             @RequestParam(defaultValue = "departureTime") String orderBy,
                                             @RequestParam(defaultValue = "asc") String orderDir,
                                             @RequestParam(defaultValue = "1900-01-01T00:00:00") String minDate,
                                             @RequestParam(defaultValue = "9999-01-01T00:00:00") String maxDate,
                                             @PageableDefault(size = 10) Pageable pageable) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (orderDir.equalsIgnoreCase("desc")) {
            direction = Sort.Direction.DESC;
        }

        LocalDateTime formattedMinDate = LocalDateTime.parse(minDate);
        LocalDateTime formattedMaxDAte = LocalDateTime.parse(maxDate);

        Sort sort = Sort.by(direction, orderBy);
        Page<BikeTripDto> bikeTripPage = tripService.searchWithTime(keyword, formattedMinDate, formattedMaxDAte, sort, pageable);

        return bikeTripPage;
    }

    @PostMapping("/upload")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            tripService.importStationCSV(file);
            return ResponseEntity.ok("CSV file processed successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing CSV file.");
        }
    }
}
