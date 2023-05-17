package com.js.portfolio_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bike_stations")
public class BikeStation {

  @Id
  private Integer fid;
  @Id
  private Integer id;

  @Column(name = "name")
  private String stationName;

  @Column(name = "address")
  private String stationAddress;

  @Column(name = "city")
  private String stationCity;

  @Column(name = "capacity")
  private Integer stationCapacity;

  @Column(name = "locationX")
  private Float stationLocationX;

  @Column(name = "locationY")
  private Float stationLocationY;

  public Integer getFid() {
    return fid;
  }

  public void setFid(Integer fid) {
    this.fid = fid;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getStationAddress() {
    return stationAddress;
  }

  public void setStationAddress(String stationAddress) {
    this.stationAddress = stationAddress;
  }

  public String getStationCity() {
    return stationCity;
  }

  public void setStationCity(String stationCity) {
    this.stationCity = stationCity;
  }

  public Integer getStationCapacity() {
    return stationCapacity;
  }

  public void setStationCapacity(Integer stationCapacity) {
    this.stationCapacity = stationCapacity;
  }

  public Float getStationLocationX() {
    return stationLocationX;
  }

  public void setStationLocationX(Float stationLocationX) {
    this.stationLocationX = stationLocationX;
  }

  public Float getStationLocationY() {
    return stationLocationY;
  }

  public void setStationLocationY(Float stationLocationY) {
    this.stationLocationY = stationLocationY;
  }
}
