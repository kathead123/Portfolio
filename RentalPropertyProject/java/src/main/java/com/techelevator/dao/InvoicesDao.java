package com.techelevator.dao;

import com.techelevator.model.Invoices;

import java.time.LocalDate;
import java.util.List;

public interface InvoicesDao {

    void setInvoicesWithLease(LocalDate leaseStartDate, int renterId, int propertyId);

    List<Invoices> getInvoicesByUserId(int userId);

    void updateInvoiceStatus (int invoiceNumber);

    List<Invoices> getInvoicesByLandlordId(int landlordUserId);
}

