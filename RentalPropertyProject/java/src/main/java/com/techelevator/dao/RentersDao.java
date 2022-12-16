package com.techelevator.dao;

import java.time.LocalDate;
import java.util.Date;

public interface RentersDao {

    int setRenterWithLease(LocalDate leaseStartDate, int userId);

}
