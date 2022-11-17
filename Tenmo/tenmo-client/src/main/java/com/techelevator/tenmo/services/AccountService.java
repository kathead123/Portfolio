package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {
    private static final String API_BASE_URL = "http://localhost:8080/";

    private final RestTemplate restTemplate = new RestTemplate();


    private String token= null;

    public void setToken(String token){
        this.token= token;
    }


    public BigDecimal getBalance(){
       BigDecimal amount= new BigDecimal("0.00");
     String url=API_BASE_URL + "balance";
        try {
            ResponseEntity<BigDecimal> response= restTemplate.exchange(url, HttpMethod.GET,makeAuthEntity(), BigDecimal.class);
            amount=response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return amount;
    }


    public void getUsers(){
        Account[] users= null;
        String url =API_BASE_URL + "users";
        try {
            ResponseEntity<Account[]> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Account[].class);
            users= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }

        System.out.println("-------------------------------------------\n" +
                "Users\n" +
                "ID          Name\n" +
                "-------------------------------------------");
        for(Account user: users) {
            if (!user.getUsername().equals(getMyUsername())) {
                System.out.println(user.getUser_id() + "       " + user.getUsername());
            }
        }
    }

    public void sendTransfer(BigDecimal amount, int userInputOfIdToSendTo){
       String url= API_BASE_URL + "sendTransfer?amount=" + amount + "&userId=" + userInputOfIdToSendTo;
       if(userInputOfIdToSendTo== getMyUserId()){
           System.out.println("Invalid entry. You cannot send money to yourself.");
       }
        try {
            ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.PUT, makeAuthEntity(), Account.class);
        }catch(HttpServerErrorException ex){
                System.out.println("The User Id or amount input is invalid. Please try again.");
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
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

    public int getMyUserId(){
        int userId= 0;
        String url= API_BASE_URL + "getMyUserId/" ;
        try {
            ResponseEntity<Integer> response= restTemplate.exchange(url, HttpMethod.GET, makeAuthEntity(), Integer.class);
            userId= response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userId;
    }




    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers);
    }





}
