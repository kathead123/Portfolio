package com.techelevator;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;


public class purchaseLog {

    File vendingInfo = new File("C:\\Users\\Student\\workspace\\capstone-1-team-9\\capstone\\vendingLog.txt");

    public void addToLogFeedMoney(BigDecimal moneyInput, Purchase purchase) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(vendingInfo, true))) {
            LocalDateTime now = LocalDateTime.now();
            writer.println( now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"))
                    + " FEED MONEY: $" + moneyInput.setScale(2) + " $" + purchase.getMoneyInput().setScale(2));


        } catch (Exception ex) {
            System.out.println("Not logged. File not found.");

        }
    }

    public void addToLogItemSelected(Purchase purchase, AllSnacks snacks, String code) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(vendingInfo, true))) {
            LocalDateTime now = LocalDateTime.now();

            writer.println(now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + " "
                    + snacks.getNameFromCode(code) + " " + code + " $"
                    + snacks.getPriceFromCode(code).setScale(2) + " $" + purchase.getMoneyInput().setScale(2));


        } catch (Exception ex) {
            System.out.println("Not logged. File not found");
        }
    }

    public void addToLogChangeGiven (Purchase purchase) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(vendingInfo, true))) {
            LocalDateTime now = LocalDateTime.now();
            writer.println( now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + " " + "GIVE CHANGE: $"
                    + purchase.getMoneyInput().setScale(2) + " $0.00");

    }catch (Exception ex) {
            System.out.println("Not logged. File not found");
        }
    }
}