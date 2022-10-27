package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class AllSnacks {

    private Map<String, Integer> snackAmount = new HashMap<>();

    private LinkedHashMap<String, String> snacksForPurchase = new LinkedHashMap<>();

    private Map<String, BigDecimal> snackPrice = new HashMap<>();
    private int quantity = 5;

    File snackFile = new File("C:\\Users\\Student\\workspace\\capstone-1-team-9\\capstone\\vendingmachine.csv");

    public AllSnacks() {

    }

    public Map<String, BigDecimal> getSnackPrice() {
        return snackPrice;
    }

    public LinkedHashMap<String, String> getSnacksForPurchase() {
        return snacksForPurchase;
    }

    public Map<String, Integer> getSnackAmount() {
        return snackAmount;
    }

    public int getQuantity(){
        return quantity;
    }

    public String displayItems() {
        for (Map.Entry<String, Integer> items : snackAmount.entrySet()) {
            if (items.getValue() == 0) {
                System.out.println("SOLD OUT");
            } else {
                System.out.println(items.getValue() + " " + items.getKey() + "(s) available");
            }
        }
        return "";
    }

    public void populateDisplayItemsMap() {
        try (Scanner scan = new Scanner(snackFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineParts = line.split("\\|");
                snackAmount.put(lineParts[1], quantity);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public String itemsForPurchase() {
        try (Scanner scan = new Scanner(snackFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineParts = line.split("\\|");
                snacksForPurchase.put(lineParts[0], lineParts[1]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        for (Map.Entry<String, String> items : snacksForPurchase.entrySet()) {
            System.out.println(items.getKey() + ": " + items.getValue());
        }
        return "";
    }

    public void populateItemsWithPriceMap() {
        try (Scanner scan = new Scanner(snackFile)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] lineParts = line.split("\\|");
                BigDecimal price = new BigDecimal(lineParts[2]);
                snackPrice.put(lineParts[1], price);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public String getNameFromCode(String code) {
        for (Map.Entry<String, String> items : snacksForPurchase.entrySet()) {
            if (items.getKey().equals(code)) {
                return items.getValue();
            }
        }
        return "";
    }

    public BigDecimal getPriceFromCode (String code) {
        String codeName = snacksForPurchase.get(code);
        for (Map.Entry<String, BigDecimal> items : snackPrice.entrySet()) {
            if (items.getKey().equals(codeName)) {
                return items.getValue();
            }
        }
        return null;
    }

    public String itemDispense(String code, Purchase purchase){
        String message = "";
        if (!snacksForPurchase.containsKey(code)) {
            message = "Invalid entry. Please try again.";
            System.out.println(message);
            return null;
        }
        String codeName= snacksForPurchase.get(code);
        int quantity = snackAmount.get(codeName);

         if (snackAmount.get(codeName) == 0){
             message = "This item is sold out! Please select another option.";
            System.out.println(message);
        } else if(snacksForPurchase.containsKey(code)) {
            BigDecimal price = snackPrice.get(snacksForPurchase.get(code));
            if (price.compareTo(purchase.getMoneyInput()) <= 0) {
                snackAmount.put(codeName, quantity - 1);

                purchase.setChangeProvided(price);
                message = "Item Name: " + snacksForPurchase.get(code) + ", Item Price: $" + price + ", Money Remaining: $" + purchase.getMoneyInput();

                if (code.contains("A")) {
                    message = message + " -Crunch Crunch, Yum!";
                    System.out.println(message);
                } else if (code.contains("B")) {
                    message = message + " -Munch Munch, Yum!";
                    System.out.println(message);
                } else if (code.contains("C")) {
                    message = message + " -Glug Glug, Yum!";
                    System.out.println(message);
                } else if (code.contains("D")) {
                    message = message + " -Chew Chew, Yum!";
                    System.out.println(message);
                }
            } else{
                message = "Insufficient funds available. Please feed more money.";
                System.out.println(message);
            }
        }
        return message;
    }


    }




