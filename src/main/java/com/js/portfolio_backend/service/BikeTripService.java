package com.js.portfolio_backend.service;

import com.js.portfolio_backend.datatransferobject.BikeTripDto;
import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.repository.BikeTripRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeTripService {

    private final BikeTripRepository tripRepository;

    public BikeTripService(BikeTripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<BikeTrip> getAll() {
        //Only for testing
        return tripRepository.findAll();
    }

    public Page<BikeTripDto> searchWithTime(String keyword, LocalDateTime minDate, LocalDateTime maxDate, Sort sort, Pageable pageable) {
        String lowerKeyword = keyword.toLowerCase();
        String matchAnyKeyword = "%" + lowerKeyword + "%";

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<BikeTripDto> trips = tripRepository.searchWithDate(matchAnyKeyword, minDate, maxDate, pageable);

        return trips;
    }


    public void importStationCSV(MultipartFile file) throws IOException {

        Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), "UTF-8"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        List<CSVRecord> records = csvParser.getRecords();

        for (int i = 0; i < records.size(); i++) {
            CSVRecord record = records.get(i);
            /*  Departure,
                Return,
                Departure station id,
                Departure station name, --> Skip, will join to bike_station table with ID
                Return station id,
                Return station name, --> Skip, will join to bike_station table with ID
                Covered distance (m), --> Needs to be over 10m
                Duration (sec.) --> Needs to be over 10s */

            LocalDateTime departureTime = LocalDateTime.parse(record.get("Departure"));
            if (departureTime == null) {
                // Skip this record, as this is a required field
                continue;
            }

            LocalDateTime returnTime = LocalDateTime.parse(record.get("Return"));
            if (returnTime == null) {
                // Skip this record, as this is a required field
                continue;
            }

            Integer departureId = Integer.parseInt(record.get("Departure station id"));
            if (departureId <= 0) {
                // Skip this record, as this is a required field
                continue;
            }

            Integer returnId = Integer.parseInt(record.get("Return station id"));
            if (returnId <= 0) {
                // Skip this record, as this is a required field
                continue;
            }

            Integer coveredDistance = Integer.parseInt(record.get("Covered distance (m)"));
            if (coveredDistance <= 10) {
                // Skip this record, as the distance needs to be over 10 meters
                continue;
            }

            Integer timeInSeconds = Integer.parseInt(record.get("Duration (sec.)"));
            if (timeInSeconds <= 10) {
                // Skip this record, as the duration needs to be over 10 seconds
                continue;
            }

            // Create an instance of YourEntity and set the values
            BikeTrip trip = new BikeTrip();
            trip.setDepartureTime(departureTime);
            trip.setDeptStationId(departureId);
            trip.setReturnTime(returnTime);
            trip.setRetStationId(returnId);
            trip.setDistanceInMeters(coveredDistance);
            trip.setDurationInSec(timeInSeconds);


            // Save the entity to the database
            tripRepository.save(trip);

        }
        csvParser.close();
    }

}
