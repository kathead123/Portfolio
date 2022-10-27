package com.techelevator;

import com.techelevator.Menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class Purchase {

    private BigDecimal moneyInput = new BigDecimal("0.00");
    private BigDecimal changeProvided = new BigDecimal("0.00");
    private BigDecimal totalBalance = new BigDecimal("0.00");
    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private BigDecimal quarterValue = new BigDecimal("0.25");
    private BigDecimal dimeValue = new BigDecimal("0.10");
    private BigDecimal nickelValue = new BigDecimal("0.05");

    public Purchase() {
    }

    public BigDecimal getMoneyInput() {
        return moneyInput;
    }

    public BigDecimal getQuarters() {
        return moneyInput.divideToIntegralValue(quarterValue);}

    public BigDecimal getDimes() {
        getQuarters();
        BigDecimal changeLeft = moneyInput.subtract((getQuarters().multiply(quarterValue)));
        return changeLeft.divideToIntegralValue(dimeValue);}

    public BigDecimal getNickels() {
        getQuarters();
        getDimes();
        BigDecimal changeLeft = moneyInput.subtract((getDimes().multiply(dimeValue)).add(getQuarters().multiply(quarterValue)));
        return changeLeft.divideToIntegralValue(nickelValue);}


    public void setMoneyInput(BigDecimal moneyInput) {
        this.moneyInput = moneyInput;
    }
    public void setChangeProvided(BigDecimal newItem) {
        this.changeProvided = moneyInput.subtract(newItem);
        moneyInput = moneyInput.subtract(newItem);
    }
    public void setQuarters(BigDecimal quarters) { this.quarters = quarters;}

    public void setDimes(BigDecimal dimes) {this.dimes = dimes;}

    public void setNickels(BigDecimal nickels) {this.nickels = nickels;}




    public void addMoney(BigDecimal moneyInput) {
       String money = moneyInput.toString();


            if (moneyInput.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0 && moneyInput.compareTo(BigDecimal.ZERO) >= 0) {
                this.moneyInput = this.moneyInput.add(moneyInput);
            }
         else if (moneyInput.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("We only accept whole dollar amounts!");
        }

        }
        public String returnChange()
            {quarters = getQuarters();
            dimes = getDimes();
            nickels = getNickels();
            String message = "Customer change: " +quarters.setScale(0) + " quarter(s), " + dimes.setScale(0) +" dime(s), " + nickels.setScale(0) + " nickel(s) ";

            System.out.println(message);


                return message;
            }

    }



