package com.techelevator.dao;

import com.techelevator.model.Invoices;
import com.techelevator.model.Properties;
import com.techelevator.model.Renters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcInvoicesDao implements InvoicesDao{


    private final JdbcTemplate jdbcTemplate;

    public JdbcInvoicesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void setInvoicesWithLease(LocalDate leaseStartDate,  int propertyId, int renterId) {


        for(int i=0; i<13; i++) {

            leaseStartDate = leaseStartDate.plusMonths(1);


        String sql = "INSERT INTO invoices(is_paid, due_date, amount_due, renter_id, property_id) " +
                "VALUES (false, ?, (SELECT rent_amount FROM properties WHERE property_id=?), ?, ?);";


        jdbcTemplate.update(sql, leaseStartDate, propertyId, renterId, propertyId);



        }
    }

    @Override
    public void updateInvoiceStatus (int invoiceNumber) {
        String sql= "Update invoices " +
                "SET is_paid = true " +
                "WHERE invoice_number = ?;";

        jdbcTemplate.update(sql, invoiceNumber);

    }

    @Override
    public List<Invoices> getInvoicesByUserId(int userId) {

            List<Invoices> invoices = new ArrayList<>();


            String sql = "SELECT renters.renter_id, lease_start_date, lease_end_date, user_id, invoice_number, is_paid, due_date, amount_due, property_id " +
                    "FROM renters " +
                    "JOIN invoices ON renters.renter_id = invoices.renter_id " +
                    "WHERE user_id = ?;";


            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);

            while(rs.next()) {
                Invoices invoice = mapRowToInvoices2(rs);
                invoices.add(invoice);
        }
            return invoices;
    }

    @Override
    public List<Invoices> getInvoicesByLandlordId(int landlordUserId) {
        List<Invoices> invoicesList= new ArrayList<>();

        String sql= "select invoice_number, is_paid, due_date, amount_due, renter_id, invoices.property_id, landlord_user_id " +
                "from invoices " +
                "join properties on invoices.property_id = properties.property_id " +
                "WHERE landlord_user_id = ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, landlordUserId);

        while(rs.next()){
            Invoices invoice= mapRowToInvoices3(rs);
            invoicesList.add(invoice);
        }
        return invoicesList;
    }


    private Invoices mapRowToInvoices(SqlRowSet rs) {
        Invoices invoice= new Invoices();
        invoice.setInvoiceNumber(rs.getInt("invoice_number"));
        invoice.setPaid(rs.getBoolean("is_paid"));
        if(rs.getDate("due_date")!=null){
            invoice.setDueDate(rs.getDate("due_date").toLocalDate());

        }
        invoice.setAmountDue(rs.getInt("amount_due"));
        invoice.setRenterId(rs.getInt("renter_id"));
        invoice.setPropertyId(rs.getInt("property_id"));
        return invoice;
    }

    private Invoices mapRowToInvoices2(SqlRowSet rs) {
        Invoices invoice= new Invoices();
        invoice.setInvoiceNumber(rs.getInt("invoice_number"));
        invoice.setPaid(rs.getBoolean("is_paid"));
        if(rs.getDate("due_date")!=null){
            invoice.setDueDate(rs.getDate("due_date").toLocalDate());

        }
        invoice.setAmountDue(rs.getInt("amount_due"));
        invoice.setRenterId(rs.getInt("renter_id"));
        invoice.setPropertyId(rs.getInt("property_id"));
        if(rs.getDate("lease_start_date")!=null){
            invoice.setLeaseStartDate(rs.getDate("lease_start_date").toLocalDate());

        }
        if(rs.getDate("lease_end_date")!=null){
            invoice.setLeaseEndDate(rs.getDate("lease_end_date").toLocalDate());

        }
        invoice.setUserId(rs.getInt("user_id"));
        return invoice;
    }

    private Invoices mapRowToInvoices3(SqlRowSet rs) {
        Invoices invoice= new Invoices();
        invoice.setInvoiceNumber(rs.getInt("invoice_number"));
        invoice.setPaid(rs.getBoolean("is_paid"));
        if(rs.getDate("due_date")!=null){
            invoice.setDueDate(rs.getDate("due_date").toLocalDate());

        }
        invoice.setAmountDue(rs.getInt("amount_due"));
        invoice.setRenterId(rs.getInt("renter_id"));
        invoice.setPropertyId(rs.getInt("property_id"));

        invoice.setLandlordUserId(rs.getInt("landlord_user_id"));
        return invoice;
    }
}
