package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    void recordFromTransfer(int fromAccount, int toAccount, BigDecimal amount);
    List<Transfer> getMyTransfers(int accountId);
    Transfer getTransferById(int transferId);
    void recordTransferRequest(int fromAccount, int toAccount, BigDecimal amount);
    List<Transfer> getMyPendingTransactions(int fromAccount);
    void recordTransferApproval(int transferId);
    void recordTransferReject(int transferId);

}
