package com.js.portfolio_backend.service;

import com.js.portfolio_backend.model.BikeStation;
import com.js.portfolio_backend.repository.BikeStationRepository;
import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class BikeStationService {
    private final BikeStationRepository stationRepository;

    public BikeStationService(BikeStationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public void importStationCSV(MultipartFile file) throws IOException {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

        for (CSVRecord record : csvParser) {
            Integer fid = Integer.parseInt(record.get("FID"));
            Integer id = Integer.parseInt(record.get("ID"));
            String name = record.get("Name");
            String address = record.get("Osoite");
            String city = record.get("Kaupunki");
            Integer capacity = Integer.parseInt(record.get("Kapasiteet"));
            Float locationX = Float.parseFloat(record.get("x"));
            Float locationY = Float.parseFloat(record.get("y"));

            // Perform validation on the data
            if (fid != 0 && id != 0) {
                //TODO: validate

                // Create an instance of YourEntity and set the values
                BikeStation station = new BikeStation();

                station.setFid(fid);
                station.setId(id);
                station.setStationName(name);
                station.setStationAddress(address);
                station.setStationCity(city);
                station.setStationCapacity(capacity);
                station.setStationLocationX(locationX);
                station.setStationLocationX(locationY);

                // Save the entity to the database
                stationRepository.save(station);
            }
        }
    }

}
