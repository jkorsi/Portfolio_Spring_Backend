package com.js.portfolio_backend.model;

import java.io.Serializable;

// Relationship ID class for Hibernate / composite key
public class BikeStationCompositeId implements Serializable {
    private Integer fid;
    private Integer id;

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
}