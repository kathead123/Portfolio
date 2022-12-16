package com.techelevator.model;

public class Properties {

    private int propertyId;
    private String address;
    private int rentAmount;
    private String propertyDescription;
    private String pictureLink;
    private int landlordUserId;
    private boolean isRented;

    public Properties(int propertyId, String address, int rentAmount, String propertyDescription, String pictureLink, int landlordUserId, boolean isRented) {
        this.propertyId = propertyId;
        this.address = address;
        this.rentAmount = rentAmount;
        this.propertyDescription = propertyDescription;
        this.pictureLink = pictureLink;
        this.landlordUserId = landlordUserId;
        this.isRented = isRented;
    }

    public Properties() {

    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(int rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public int getLandlordUserId() {
        return landlordUserId;
    }

    public void setLandlordUserId(int landlordUserId) {
        this.landlordUserId = landlordUserId;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
