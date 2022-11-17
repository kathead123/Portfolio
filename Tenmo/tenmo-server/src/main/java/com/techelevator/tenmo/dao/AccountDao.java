package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    BigDecimal getBalance(int userId);

    List<Account> getAccounts();

    List<Account> getUsers();

    Account getAccountById(int userId);

    void updateBalance(BigDecimal balance, int userId);

    int getAccountIdFromUsername(String username);

    int getAccountIdFromUserId(int userId);

    int getUserIdFromUsername(String username);

    String getUsernameFromAccountId(int accountId);


}
