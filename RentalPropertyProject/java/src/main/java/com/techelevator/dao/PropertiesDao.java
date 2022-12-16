package com.techelevator.dao;

import com.techelevator.model.Properties;

import java.util.List;

public interface PropertiesDao {

    List<Properties> getAllAvailableProperties();

    List<Properties> getPropertiesByLandlordId(int landlordUserId);

    void addProperty(String address, int rentAmount, String propertyDescription, String pictureLink, int landlordUserId);

    Properties getPropertiesByPropertyId(int propertyId);

    void updatePropertyStatus (int propertyId);
}
