package com.techelevator.controller;

import com.techelevator.dao.PropertiesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Properties;

import com.techelevator.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class PropertiesController {

    private UserDao userDao;

    private PropertiesDao propertiesDao;

    public PropertiesController(PropertiesDao propertiesDao, UserDao userDao) {
        this.propertiesDao= propertiesDao; this.userDao= userDao;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Properties> getAllPropertiesAvailable() {

        return propertiesDao.getAllAvailableProperties();
    }

    @RequestMapping(value = "/landlord-properties", method = RequestMethod.GET)
    public List<Properties> getPropertiesByLandlordId(Principal principal) {

        return propertiesDao.getPropertiesByLandlordId(userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(value = "/get-property/{propertyId}", method = RequestMethod.GET)
    public Properties getPropertiesByPropertyId(@PathVariable int propertyId) {

        return propertiesDao.getPropertiesByPropertyId(propertyId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/add-property", method = RequestMethod.POST)
    public void addProperty(Principal principal, @RequestBody Properties property) {
        propertiesDao.addProperty(property.getAddress(), property.getRentAmount(), property.getPropertyDescription(), property.getPictureLink(), userDao.findIdByUsername(principal.getName()));
    }

}
