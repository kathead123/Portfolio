package com.techelevator.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;


public class Invoices {

    int invoiceNumber;
    boolean isPaid;
    LocalDate dueDate;
    int amountDue;
    int renterId;
    int propertyId;
    LocalDate leaseStartDate;
    LocalDate leaseEndDate;
    int userId;
    int landlordUserId;


    public Invoices(int invoiceNumber, boolean isPaid, LocalDate dueDate, int amountDate, int renterId, int propertyId) {
        this.invoiceNumber = invoiceNumber;
        this.isPaid = isPaid;
        this.dueDate = dueDate;
        this.amountDue = amountDate;
        this.renterId = renterId;
        this.propertyId = propertyId;
    }

    public Invoices() {

    }

    public int getLandlordUserId() {
        return landlordUserId;
    }

    public void setLandlordUserId(int landlordUserId) {
        this.landlordUserId = landlordUserId;
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

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDate) {
        this.amountDue = amountDate;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
