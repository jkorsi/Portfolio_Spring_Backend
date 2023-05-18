package com.js.portfolio_backend.service;

import com.js.portfolio_backend.model.BikeStation;
import com.js.portfolio_backend.repository.BikeStationRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class BikeStationService {
    private final BikeStationRepository stationRepository;

    public BikeStationService(BikeStationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<BikeStation> getAll(){
        return stationRepository.findAll();
    }

    public void importStationCSV(MultipartFile file) throws IOException {

        Reader reader = new BufferedReader(new InputStreamReader(new BOMInputStream(file.getInputStream()), "UTF-8"));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        List<CSVRecord> records = csvParser.getRecords();

        for(int i = 0; i < records.size(); i++){
            CSVRecord record = records.get(i);

            record.get("ID");

            Integer fid = Integer.parseInt(record.get("FID"));
            Integer id = Integer.parseInt(record.get("ID"));
            String name = record.get("Name");
            String address = record.get("Osoite");
            String city = record.get("Kaupunki");
            Integer capacity = Integer.parseInt(record.get("Kapasiteet"));
            Float locationX = Float.parseFloat(record.get("x"));
            Float locationY = Float.parseFloat(record.get("y"));

            //TODO: validate
            // Create an instance of YourEntity and set the values
            if(fid >= 0 && id >= 0) {
                BikeStation station = new BikeStation();

                station.setFid(fid);
                station.setId(id);
                station.setStationName(name);
                station.setStationAddress(address);
                station.setStationCity(city);
                station.setStationCapacity(capacity);
                station.setStationLocationX(locationX);
                station.setStationLocationY(locationY);

                // Save the entity to the database
                stationRepository.save(station);
            }

        }
        csvParser.close();
    }

}
