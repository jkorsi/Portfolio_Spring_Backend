package com.js.portfolio_backend.service;

import com.js.portfolio_backend.datatransferobject.BikeTripDto;
import com.js.portfolio_backend.model.BikeTrip;
import com.js.portfolio_backend.repository.BikeTripRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BikeTripService {

    private final BikeTripRepository tripRepository;
    private final JdbcTemplate jdbcTemplate;

    public BikeTripService(BikeTripRepository tripRepository, JdbcTemplate jdbcTemplate) {
        this.tripRepository = tripRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BikeTrip> getAll() {
        //Only for testing
        return tripRepository.findAll();
    }

    public Page<BikeTripDto> searchWithTime(String keyword, LocalDateTime minDate, LocalDateTime maxDate, Sort sort, Pageable pageable) {
        String lowerKeyword = keyword.toLowerCase();
        String matchAnyKeyword = "%" + lowerKeyword + "%";

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return tripRepository.searchWithDate(matchAnyKeyword, minDate, maxDate, pageable);
    }

    @Transactional
    public void importBikeTripCSV(MultipartFile file) throws IOException {

        Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), StandardCharsets.UTF_8));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        List<CSVRecord> records = csvParser.getRecords();
        List<BikeTrip> trips = new ArrayList<>();
        Set<Integer> hashes = new HashSet<>();

        for (int i = 0; i < records.size(); i++) {
            CSVRecord record = records.get(i);

            try {
                LocalDateTime departureTime = LocalDateTime.parse(record.get("Departure"));
                LocalDateTime returnTime = LocalDateTime.parse(record.get("Return"));
                Integer departureId = Integer.parseInt(record.get("Departure station id"));
                Integer returnId = Integer.parseInt(record.get("Return station id"));
                Integer coveredDistance = Integer.parseInt(record.get("Covered distance (m)"));
                Integer timeInSeconds = Integer.parseInt(record.get("Duration (sec.)"));

                // validation
                if (departureId <= 0 || returnId <= 0 || coveredDistance <= 10 || timeInSeconds <= 10) {
                    System.out.println("Record at line " + (i + 1) + " skipped due to invalid data");
                    continue;
                }

                // Hash the meaningful fields to check for duplicates
                int hash = Objects.hash(departureTime, returnTime, departureId, returnId, coveredDistance, timeInSeconds);
                if (!hashes.add(hash)) {
                    System.out.println("Record at line " + (i + 1) + " skipped due to duplicate data");
                    continue;
                }

                Double distInKm = Double.valueOf(coveredDistance) / 1000.0;
                Double kmInTwoDecimals = Math.round(distInKm * 100.0) / 100.0;

                UUID newUUID = UUID.randomUUID();

                BikeTrip trip = new BikeTrip();
                trip.setTripId(newUUID);
                trip.setDepartureTime(departureTime);
                trip.setDeptStationId(departureId);
                trip.setReturnTime(returnTime);
                trip.setRetStationId(returnId);
                trip.setDistanceInMeters(coveredDistance);
                trip.setDistanceInKm(kmInTwoDecimals);
                trip.setDurationInSec(timeInSeconds);
                trip.setDurationInMin(Math.floorDiv(timeInSeconds, 60));

                trips.add(trip);

                if(i%1000 == 0){
                    System.out.println(trips);
                    System.out.println("Round: " + i);
                }

                if (i % 30 == 0) {
                    batchInsert(trips);
                    trips.clear(); // clear the list for the next batch
                }

            } catch (Exception e) {
                // logging exception and skipping to the next record
                System.out.println("Failed to process record at line " + (i + 1) + ": " + e.getMessage());
            }
        }

        if (!trips.isEmpty()) {
            batchInsert(trips);
        }
        csvParser.close();
    }


    public void batchInsert(List<BikeTrip> trips) {
        jdbcTemplate.batchUpdate("INSERT INTO " +
                        "bike_trip (" +
                        "trip_id, " +
                        "departure_time, " +
                        "dept_station_id, " +
                        "return_time, " +
                        "ret_station_id, " +
                        "distance_in_meters, " +
                        "distance_in_km, " +
                        "duration_in_sec, " +
                        "duration_in_min) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                BikeTrip trip = trips.get(i);

                ps.setObject(1, trip.getTripId());
                ps.setObject(2, trip.getDepartureTime());
                ps.setInt(3, trip.getDeptStationId());
                ps.setObject(4, trip.getReturnTime());
                ps.setInt(5, trip.getRetStationId());
                ps.setInt(6, trip.getDistanceInMeters());
                ps.setDouble(7, trip.getDistanceInKm());
                ps.setInt(8, trip.getDurationInSec());
                ps.setInt(9, trip.getDurationInMin());
            }

            @Override
            public int getBatchSize() {
                return trips.size();
            }
        });
    }

}
