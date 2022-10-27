package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class salesReport extends Purchase{

    LinkedHashMap<String, Integer> salesReportMap= new LinkedHashMap<>();
    BigDecimal totalSales= new BigDecimal("0");

    public salesReport(){
        populateMap();
        totalSales=new BigDecimal("0");
    }

    public void populateMap(){
        AllSnacks snacks= new AllSnacks();
        snacks.populateDisplayItemsMap();

        for(Map.Entry<String, Integer> newMapItem: snacks.getSnackAmount().entrySet()){
            salesReportMap.put(newMapItem.getKey(), 0);
        }
    }

    public void updateMapQuantity(String userCodeInput, AllSnacks snacks){
        String name= snacks.getNameFromCode(userCodeInput);
        salesReportMap.put(name, salesReportMap.get(name)+1);
    }

    public void addToTotalSales(String codeInput, AllSnacks snacks){
        BigDecimal priceOfItem= snacks.getPriceFromCode(codeInput);
        totalSales= totalSales.add(priceOfItem);
    }
    public BigDecimal getTotalSales(){
        return totalSales;
    }



    public void getSalesReportFile(){
        LocalDateTime now = LocalDateTime.now();
        File file= new File( "Sales report at " +
                now.format(DateTimeFormatter.ofPattern("MM-dd-yyyy-hh:mm:ss-a")).replaceAll(":", "-") +".txt");
        try(PrintWriter writer= new PrintWriter(file)){
            file.createNewFile();
            for(Map.Entry<String, Integer> values: salesReportMap.entrySet()){
                writer.println(values.getKey() + "|" + values.getValue());
            }
            writer.println("");
            writer.println("**TOTAL SALES** $" + totalSales);



        } catch(Exception ex){
            System.out.println("File not found.");
        }

    }

}
