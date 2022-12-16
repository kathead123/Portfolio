package com.techelevator.dao;

import com.techelevator.model.Renters;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcRentersDao implements RentersDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRentersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int setRenterWithLease(LocalDate leaseStartDate, int userId) {
        int renterId= 0;
        LocalDate endDate= leaseStartDate.plusYears(1);
        String sql = "INSERT INTO renters (lease_start_date, lease_end_date, user_id) VALUES (?,?, ?) RETURNING renter_id;";

        renterId= jdbcTemplate.queryForObject(sql, Integer.class,leaseStartDate, endDate, userId);
        return renterId;
    }


    private Renters mapRowToRenter(SqlRowSet rs) {
        Renters renter= new Renters();
        renter.setRenterId(rs.getInt("renter_id"));
        if(rs.getDate("lease_start_date")!=null) {
            renter.setLeaseStartDate(rs.getDate("lease_start_date").toLocalDate());
        }
        if(rs.getDate("lease_end_date")!=null) {
            renter.setLeaseStartDate(rs.getDate("lease_end_date").toLocalDate());
        }
        renter.setUserId(rs.getInt("user_id"));
        return renter;
    }
}
