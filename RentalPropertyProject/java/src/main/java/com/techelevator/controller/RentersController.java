package com.techelevator.controller;

import com.techelevator.dao.InvoicesDao;
import com.techelevator.dao.PropertiesDao;
import com.techelevator.dao.RentersDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Invoices;
import com.techelevator.model.Properties;
import com.techelevator.model.Renters;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class RentersController {

    private UserDao userDao;
    private RentersDao rentersDao;
    private InvoicesDao invoicesDao;

    private PropertiesDao propertiesDao;

    public RentersController(UserDao userDao, RentersDao rentersDao, InvoicesDao invoicesDao, PropertiesDao propertiesDao) {
        this.userDao= userDao;
        this.rentersDao= rentersDao;
        this.invoicesDao= invoicesDao;
        this.propertiesDao= propertiesDao;

    }

    @RequestMapping(value = "/list-renters/{propertyId}", method = RequestMethod.GET)
    public List<User> getAllRenters() {

        return userDao.getAllRenters();
    }

    @RequestMapping(value = "/list-renters/{propertyId}", method = RequestMethod.POST)
    public void setRenterWithLease(@RequestBody Renters renter, @PathVariable int propertyId) {
        int renterId= rentersDao.setRenterWithLease(renter.getLeaseStartDate(), renter.getUserId());
        invoicesDao.setInvoicesWithLease(renter.getLeaseStartDate(), propertyId, renterId );
        propertiesDao.updatePropertyStatus(propertyId);
    }

    @RequestMapping(value = "/list-invoices", method = RequestMethod.GET)
    public List<Invoices> getInvoicesByUserId(Principal principal) {

        return invoicesDao.getInvoicesByUserId(userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(value = "/pay-invoice/{invoiceNumber}", method = RequestMethod.PUT)
    public void updateInvoiceStatus(@PathVariable int invoiceNumber) {
        invoicesDao.updateInvoiceStatus(invoiceNumber);
    }

    @RequestMapping(value = "/view-invoices-status", method = RequestMethod.GET)
    public List<Invoices> getInvoicesByLandlordId(Principal principal) {
        return invoicesDao.getInvoicesByLandlordId(userDao.findIdByUsername(principal.getName()));
    }
}
