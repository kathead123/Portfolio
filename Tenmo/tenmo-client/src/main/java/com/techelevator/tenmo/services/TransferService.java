package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;
import org.bouncycastle.crypto.DataLengthException;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.math.BigDecimal;
import java.util.List;

public class TransferService {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final RestTemplate restTemplate = new RestTemplate();

    ConsoleService consoleService= new ConsoleService();

    private String token= null;

    public void setToken(String token){
        this.token= token;
    }

    private String username= null;

    public void setUsername(String username){
        this.username= username;
    }

    public String getUsernameFromAccountId(int accountId){
        String username= null;
        String url= API_BASE_URL + "getUsername/" + accountId;
        try {
            ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), String.class);
            username= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return username;
    }

    public void requestMoney(BigDecimal amount, int userIdToRequestFrom){
        String url= API_BASE_URL + "requestTransfer?amount=" + amount + "&userId=" + userIdToRequestFrom;
        try {
            ResponseEntity<Transfer> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    public void getTransfers(){
        Transfer[] transfers= null;
        String url= API_BASE_URL + "transfers";
        try {
            ResponseEntity<Transfer[]> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        System.out.println("-------------------------------------------\n" +
                "Transfers\n" +
                "ID          From/To                 Amount\n" +
                "-------------------------------------------");
        for(Transfer transfer: transfers){
            int accountFromId= transfer.getAccountFrom();
            int accountToId= transfer.getAccountTo();

                    if(getMyUsername().equals(getUsernameFromAccountId(accountFromId))) {
                        System.out.printf("%-11d%-6s%-15s%5s%4.2f\n",transfer.getTransferId() , "To:" , getUsernameFromAccountId(accountToId) ,   "$" , transfer.getAmount());
                    } else if (getMyUsername().equals(getUsernameFromAccountId(accountToId))){
                        System.out.printf("%-11d%-6s%-15s%5s%4.2f\n",transfer.getTransferId() , "From:" , getUsernameFromAccountId(accountFromId) ,   "$" , transfer.getAmount());
                    }
        }
    }
    public void transferApproval(int transferId){
        String url = API_BASE_URL + "approveTransfer?transferId=" + transferId;
        try {
            ResponseEntity<Transfer> response= restTemplate.exchange(url, HttpMethod.PUT, makeAuthEntity(), Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }
    public void getTransferDetails(int transferId){
        Transfer transfer = null;
        String url = API_BASE_URL + "transfers/" + transferId;
        if(transferId!=0 ) {
            try {
                ResponseEntity<Transfer> response = restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Transfer.class);
                transfer = response.getBody();
                System.out.println("--------------------------------------------\n" +
                        "Transfer Details\n" +
                        "--------------------------------------------");
                System.out.println("Id: " + transfer.getTransferId() + "\n" +
                        "From: " + getUsernameFromAccountId(transfer.getAccountFrom()) + "\n" +
                        "To: " + getUsernameFromAccountId(transfer.getAccountTo()) + "\n" +
                        "Type: " + transfer.getTransferTypeDesc() + "\n" +
                        "Status: " + transfer.getTransferStatusDesc() + "\n" +
                        "Amount: $" + transfer.getAmount());
            } catch(NullPointerException e){
                System.out.println("Invalid Transfer Id. Please try again.");
            }

            catch (RestClientResponseException | ResourceAccessException e) {
                BasicLogger.log(e.getMessage());
            }

        }
        else if (transferId==0){

        }

    }

    public Transfer getTransferById(int transferId){
        Transfer transfer= null;
        String url= API_BASE_URL + "transfers/" +transferId ;
        try {
            ResponseEntity<Transfer> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Transfer.class);
            transfer= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transfer;
    }

    public String getMyUsername(){
        String username= null;
        String url= API_BASE_URL + "getMyUsername/" ;
        try {
            ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), String.class);
            username= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return username;
    }

    public void rejectTransfer(int transferId){
        String url= API_BASE_URL + "rejectTransfer?transferId=" + transferId;
        try {
            ResponseEntity<Transfer> response= restTemplate.exchange(url, HttpMethod.PUT, makeAuthEntity(), Transfer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    public void getMyPendingTransfers(){
        Transfer[] transfers = null;
        String url = API_BASE_URL + "pendingTransfers" ;
        try {
            ResponseEntity<Transfer[]> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Transfer[].class);
            transfers = response.getBody();
            System.out.println("-------------------------------------------\n" +
                    "Pending Transfers\n" +
                    "ID          To                     Amount\n" +
                    "-------------------------------------------");
            if(transfers.length==0){
                System.out.println("You have no pending transfer requests. Please make another selection.");
                System.out.println("");
            }
            for (Transfer transfer: transfers){
                System.out.printf("%-11d%-23s%-2s%4.2f\n",transfer.getTransferId() , getUsernameFromAccountId(transfer.getAccountTo()) ,"$" ,transfer.getAmount());
            }
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }


    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers);
    }


}
