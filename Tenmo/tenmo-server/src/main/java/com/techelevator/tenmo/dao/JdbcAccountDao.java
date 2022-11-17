package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    @Override
    public BigDecimal getBalance(int userId){
        BigDecimal balance= new BigDecimal("0.00");

        String sql="SELECT balance " +
                "FROM account " +
                "WHERE user_id=? ;";

        BigDecimal result= jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

        balance= result;

        return balance;
    }

    @Override
    public List<Account> getAccounts(){
        List<Account> accounts= new ArrayList<>();
        String sql= "SELECT account_id, user_id, balance " +
                "FROM account;";
        SqlRowSet result= jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            Account account= mapRowToAccount(result);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int userId){
        Account account= null;
        String sql= "SELECT account_id, user_id, balance " +
                "FROM account " +
                "WHERE user_id= ?;";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql, userId );
        while(results.next()){
            account= mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public List<Account> getUsers(){
        List<Account> users= new ArrayList<>();
        String sql= "SELECT account.user_id, username, account.account_id " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id= tenmo_user.user_id;" ;
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            Account account= new Account();
            account.setUser_id(results.getInt("user_id"));
            account.setAccount_id(results.getInt("account_id"));
            account.setUsername(results.getString("username"));
            users.add(account);
        }
        return users;
    }

    @Override
    public int getAccountIdFromUsername(String username){
        String sql= "SELECT account_id " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id= tenmo_user.user_id " +
                "WHERE username= ?;";
        int accountId= jdbcTemplate.queryForObject(sql, Integer.class, username);

        return accountId;
    }

    @Override
    public String getUsernameFromAccountId(int accountId){
        String sql= "SELECT username " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id= tenmo_user.user_id " +
                "WHERE account_id= ?;";
        String username= jdbcTemplate.queryForObject(sql, String.class, accountId);

        return username;
    }

    @Override
    public int getAccountIdFromUserId(int userId){
        String sql= "SELECT account_id " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id= tenmo_user.user_id " +
                "WHERE account.user_id= ?;";
        int accountId= jdbcTemplate.queryForObject(sql, Integer.class, userId);

        return accountId;
    }

    @Override
    public int getUserIdFromUsername(String username){
        String sql= "SELECT account.user_id " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id= tenmo_user.user_id " +
                "WHERE username= ?;";
        int userId= jdbcTemplate.queryForObject(sql, Integer.class, username);

        return userId;
    }

    @Override
    public void updateBalance(BigDecimal balance, int userId){
        String sql= "UPDATE account SET balance= ? " +
                "WHERE user_id= ?;";
        jdbcTemplate.update(sql, balance, userId);
    }



    public Account mapRowToAccount(SqlRowSet result){
        Account account= new Account();
        account.setAccount_id(result.getInt("account_id"));
        account.setUser_id(result.getInt("user_id"));
        account.setBalance(result.getBigDecimal("balance"));
        return account;
    }
}
