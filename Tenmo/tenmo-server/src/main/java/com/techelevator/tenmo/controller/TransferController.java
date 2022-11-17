package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    UserDao userDao= null;
    TransferDao transferDao= null;
    AccountDao accountDao= null;

    public TransferController(UserDao userDao, TransferDao transferDao, AccountDao accountDao){
        this.userDao= userDao;
        this.transferDao= transferDao;
        this.accountDao= accountDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/transfers", method = RequestMethod.GET)
    public List<Transfer> getMyTransfers(Principal principal){
        int accountId= accountDao.getAccountIdFromUsername(principal.getName());
        return transferDao.getMyTransfers(accountId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransferById(@PathVariable int id){
        return transferDao.getTransferById(id);
    }


    @RequestMapping(path="/pendingTransfers", method = RequestMethod.GET)
    public List<Transfer> getMyPendingTransfers(Principal principal){
        int currentUserAccountId= accountDao.getAccountIdFromUsername(principal.getName());
        return transferDao.getMyPendingTransactions(currentUserAccountId);
    }
    @RequestMapping(path="/approveTransfer", method = RequestMethod.PUT)
    public void approveTransfer(@RequestParam int transferId){

        Transfer transfer = getTransferById(transferId);
        int senderAccountId = transfer.getAccountFrom();
        int receiverAccountId = transfer.getAccountTo();
        String senderUsername = accountDao.getUsernameFromAccountId(senderAccountId);
        String receiverUsername = accountDao.getUsernameFromAccountId(receiverAccountId);
        int senderUserId = accountDao.getUserIdFromUsername(senderUsername);
        int receiverUserId = accountDao.getUserIdFromUsername(receiverUsername);
        BigDecimal transferAmount = transfer.getAmount();
        BigDecimal senderCurrentBalance = accountDao.getBalance(senderUserId);
        BigDecimal receiverCurrentBalance = accountDao.getBalance(receiverUserId);
        BigDecimal senderNewBalance = senderCurrentBalance.subtract(transferAmount);
        BigDecimal receiverNewBalance = receiverCurrentBalance.add(transferAmount);
        if(senderCurrentBalance.compareTo(transferAmount)>0 && transferAmount.compareTo(new BigDecimal("0.00"))> 0 ) {
            accountDao.updateBalance(senderNewBalance, senderUserId);
            accountDao.updateBalance(receiverNewBalance, receiverUserId);
            transferDao.recordTransferApproval(transferId);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid transfer, please try again");
        }
    }


    @RequestMapping(path="/requestTransfer", method= RequestMethod.GET)
    public void requestTransfer(Principal principal, @RequestParam BigDecimal amount, @RequestParam int userId){
        //Get UserIds
        int currentUserId= userDao.findIdByUsername(principal.getName());
        int sendersUserId= userDao.findIdByUsername(principal.getName());
        //Get current Balances
        BigDecimal currentBalanceRequester= accountDao.getBalance(currentUserId);
        BigDecimal currentBalanceReceiver= accountDao.getBalance(userId);

        if(amount.compareTo(new BigDecimal("0.00"))>0 &&
                accountDao.getUserIdFromUsername(principal.getName())!=userId && userDao.getUserById(userId) != null ){
            transferDao.recordTransferRequest(accountDao.getAccountIdFromUserId(userId), accountDao.getAccountIdFromUsername(principal.getName()), amount);
        }
    }

    @RequestMapping(path="/rejectTransfer", method=RequestMethod.PUT)
    public void rejectTransfer(@RequestParam int transferId){
        transferDao.recordTransferReject(transferId);
    }

}
