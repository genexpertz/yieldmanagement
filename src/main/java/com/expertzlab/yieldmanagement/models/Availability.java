package com.expertzlab.yieldmanagement.models;

import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public class Availability {
    String region;
    String date;
    String availablity;
    String status;

    public String getAvailablity() {
        return availablity;
    }

    public void setAvailablity(String availablity) {
        this.availablity = availablity;
    }

    public String getRegion() {
        return region;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate() {
    }
}
