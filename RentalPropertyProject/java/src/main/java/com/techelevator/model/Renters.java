package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Renters {
    private int renterId;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private int userId;

    public Renters(int renterId, LocalDate leaseStartDate, LocalDate leaseEndDate, int userId) {
        this.renterId = renterId;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.userId = userId;
    }

    public Renters() {

    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
