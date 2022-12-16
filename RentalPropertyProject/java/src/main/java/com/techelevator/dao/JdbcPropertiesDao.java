package com.techelevator.dao;

import com.techelevator.model.Properties;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcPropertiesDao implements PropertiesDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcPropertiesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Properties> getAllAvailableProperties() {
        List<Properties> propertiesList= new ArrayList<>();

        String sql= "SELECT property_id, address, rent_amount, property_description, picture_link, landlord_user_id, is_rented " +
                "FROM properties " +
                "WHERE is_rented= 'false' ";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);

        while(rs.next()){
            Properties property= mapRowToProperties(rs);
            propertiesList.add(property);
        }
        return propertiesList;
    }

    @Override
    public List<Properties> getPropertiesByLandlordId(int landlordUserId) {
        List<Properties> propertiesList= new ArrayList<>();

        String sql= "SELECT property_id, address, rent_amount, property_description, picture_link, landlord_user_id, is_rented " +
                "FROM properties " +
                "WHERE landlord_user_id= ? ";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, landlordUserId);

        while(rs.next()){
            Properties property= mapRowToProperties(rs);
            propertiesList.add(property);
        }
        return propertiesList;
    }

    @Override
    public Properties getPropertiesByPropertyId(int propertyId) {
        Properties property= new Properties();

        String sql= "SELECT property_id, address, rent_amount, property_description, picture_link, landlord_user_id, is_rented " +
                "FROM properties " +
                "WHERE property_id= ? ;";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, propertyId);


        if(rs.next()){
            Properties newProperty= mapRowToProperties(rs);
            property= newProperty;
        }

        return property;

    }

    @Override
    public void addProperty(String address, int rentAmount, String propertyDescription, String pictureLink, int landlordUserId) {

        String sql= "INSERT INTO properties (address, rent_amount, property_description, picture_link, landlord_user_id, is_rented) VALUES (?, ?, ?, ?, ?, FALSE);";

        jdbcTemplate.update(sql, address, rentAmount, propertyDescription, pictureLink, landlordUserId);
    }

    @Override
    public void updatePropertyStatus (int propertyId) {
        String sql= "UPDATE properties " +
                "SET is_rented= true " +
                "WHERE property_id=?;";

        jdbcTemplate.update(sql, propertyId);

    }





    private Properties mapRowToProperties(SqlRowSet rs) {
        Properties property= new Properties();
        property.setPropertyId(rs.getInt("property_id"));
        property.setAddress(rs.getString("address"));
        property.setRentAmount(rs.getInt("rent_amount"));
        property.setPropertyDescription(rs.getString("property_description"));
        property.setPictureLink(rs.getString("picture_link"));
        property.setLandlordUserId(rs.getInt("landlord_user_id"));
        property.setRented(rs.getBoolean("is_rented"));
        return property;
    }

}


