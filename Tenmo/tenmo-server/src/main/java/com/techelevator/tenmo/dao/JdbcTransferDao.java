package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate= jdbcTemplate;
    }

    @Override
    public void recordFromTransfer(int fromAccount, int toAccount, BigDecimal amount){
        String sql= "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?,?,?,?,?);";
                  jdbcTemplate.update(sql, 2, 2, fromAccount, toAccount, amount);
    }

    @Override
    public void recordTransferRequest(int fromAccount, int toAccount, BigDecimal amount){
        String sql= "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?,?,?,?,?);";
                jdbcTemplate.update(sql, 1, 1, fromAccount, toAccount, amount);
    }
    @Override
    public void recordTransferApproval(int transferId){
        String sql= "update transfer set transfer_status_id = 2\n" +
                "where transfer_id = ?;";
        jdbcTemplate.update(sql, transferId);
    }

    @Override
    public void recordTransferReject(int transferId){
        String sql= "update transfer set transfer_status_id = 3\n" +
                "where transfer_id = ?;";
        jdbcTemplate.update(sql, transferId);
    }

    @Override
    public List<Transfer> getMyPendingTransactions(int fromAccount){
        List<Transfer> transfers= new ArrayList<>();
        String sql= "SELECT transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, account_from, account_to, amount, transfer_type_desc, transfer_status_desc " +
                "FROM transfer " +
                "JOIN transfer_status ON transfer.transfer_status_id= transfer_status.transfer_status_id " +
                "JOIN transfer_type ON transfer.transfer_type_id= transfer_type.transfer_type_id " +
                "WHERE account_from= ? AND transfer.transfer_status_id=1;";
        SqlRowSet result= jdbcTemplate.queryForRowSet(sql, fromAccount);
        while(result.next()){
            Transfer transfer= mapRowToTransfer(result);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        Transfer transfer = null;
            String sql = "SELECT transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, account_from, account_to, amount, transfer_type_desc, transfer_status_desc " +
                    "FROM transfer " +
                    "JOIN transfer_status ON transfer.transfer_status_id= transfer_status.transfer_status_id " +
                    "JOIN transfer_type ON transfer.transfer_type_id= transfer_type.transfer_type_id " +
                    "WHERE transfer_id=?;";
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
            while (result.next()) {
                 transfer = mapRowToTransfer(result);
            }
            return transfer;
        }

    @Override
    public List<Transfer> getMyTransfers(int accountId) {
        List<Transfer> transfers= new ArrayList<>();
        String sql = "SELECT transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, account_from, account_to, amount, transfer_type_desc, transfer_status_desc " +
                "FROM transfer " +
                "JOIN transfer_status ON transfer.transfer_status_id= transfer_status.transfer_status_id " +
                "JOIN transfer_type ON transfer.transfer_type_id= transfer_type.transfer_type_id " +
                "WHERE account_from= ? OR account_to= ?;";
        SqlRowSet result= jdbcTemplate.queryForRowSet(sql, accountId, accountId);
        while(result.next()){
            Transfer transfer= mapRowToTransfer(result);
            transfers.add(transfer);
        }
        return transfers;
    }


    public Transfer mapRowToTransfer(SqlRowSet result){
        Transfer transfer= new Transfer();
        transfer.setTransferId(result.getInt("transfer_id"));
        transfer.setTransferTypeId(result.getInt("transfer_type_id"));
        transfer.setTransferStatusId(result.getInt("transfer_status_id"));
        transfer.setAccountFrom(result.getInt("account_from"));
        transfer.setAccountTo(result.getInt("account_to"));
        transfer.setAmount(result.getBigDecimal("amount"));
        transfer.setTransferTypeDesc(result.getString("transfer_type_desc"));
        transfer.setTransferStatusDesc(result.getString("transfer_status_desc"));
        return transfer;
    }




}
