package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {

    AccountDao accountDao= null;
    UserDao userDao= null;
    TransferDao transferDao= null;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao){
        this.accountDao= accountDao;
        this.userDao= userDao;
        this.transferDao= transferDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/balance", method= RequestMethod.GET)
    public BigDecimal getBalance(Principal principal){
        int id= userDao.findIdByUsername(principal.getName());
        return accountDao.getBalance(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/users", method= RequestMethod.GET)
    public List<Account> getUsers(){
        return accountDao.getUsers();

    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path="/sendTransfer", method= RequestMethod.PUT)
    public void sendTransfer(Principal principal, @RequestParam BigDecimal amount, @RequestParam int userId){
        //Get current Balances
        BigDecimal currentBalanceSender= getBalance(principal);
        BigDecimal currentBalanceReceiver= accountDao.getBalance(userId);
        //Get senders UserId
        int sendersUserId= userDao.findIdByUsername(principal.getName());

        if(currentBalanceSender.compareTo(amount)>0 && amount.compareTo(new BigDecimal("0.00"))>0 &&
                accountDao.getUserIdFromUsername(principal.getName())!=userId && userDao.getUserById(userId) != null ) {
            //Calculate new Balances
            BigDecimal newBalanceSender= currentBalanceSender.subtract(amount);
            BigDecimal newBalanceReceiver=currentBalanceReceiver.add(amount);
            //Updating Balances
            accountDao.updateBalance(newBalanceSender, sendersUserId);
            accountDao.updateBalance(newBalanceReceiver, userId);
            //End of Transfer

            //Record Transfer on table
            transferDao.recordFromTransfer(accountDao.getAccountIdFromUsername(principal.getName()), accountDao.getAccountIdFromUserId(userId), amount);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Transfer. Please try again.");
        }

    }




    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/myAccount", method= RequestMethod.GET)
    public Account getMyAccount (Principal principal){
        int id = userDao.findIdByUsername(principal.getName());
        return  accountDao.getAccountById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/getMyUsername", method= RequestMethod.GET)
    public String getMyUsername(Principal principal){
        return principal.getName();
    }

    @RequestMapping(path="/getMyUserId", method= RequestMethod.GET)
    public int getMyUserId(Principal principal){
        String username= principal.getName();
        int userId= accountDao.getUserIdFromUsername(username);
        return userId;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path= "/getUsername/{accountId}", method = RequestMethod.GET)
    public String getUsernameFromAccountId(@PathVariable int accountId){
        return accountDao.getUsernameFromAccountId(accountId);
    }








}
