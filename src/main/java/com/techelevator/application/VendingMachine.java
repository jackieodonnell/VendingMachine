package com.techelevator.application;

import com.techelevator.logger.Logger;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.techelevator.Helpers.ChangeHelper;

public class VendingMachine implements Change {

    Logger logger;
    public VendingMachine() {
        logger = new Logger("Audit.txt");
    }
    public void run() {
        List<Item> inventory = readFile("catering2.txt");
        BigDecimal currentMoneyProvided = new BigDecimal("0.00");
        while(true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            System.out.println(choice);
            if(choice.equals("display")) {
                UserOutput.displayInventory(inventory);
            }
            else if(choice.equals("purchase")) {
                while (true) {
                    UserOutput.displayPurchaseScreen();
                    String purchaseChoice = UserInput.getPurchaseScreenOption(currentMoneyProvided);
                    if (purchaseChoice.equals("feed money")) {
                        BigDecimal moneyInserted = UserInput.feedMoney();
                        currentMoneyProvided = currentMoneyProvided.add(moneyInserted);
                        String moneyInsertedFormat = "$" + moneyInserted + ".00";
                        String currentMoneyProvidedFormat = "$" + currentMoneyProvided;
                        String writeAudit = String.format("%tD %tr %-18s %-3s %8s   %5s", LocalDate.now(), LocalTime.now().withNano(0),
                                "MONEY FED:", " ",moneyInsertedFormat, currentMoneyProvidedFormat);
                        logger.write(writeAudit);
                    } else if (purchaseChoice.equals("select item")) {
                        Item itemSelection = UserInput.selectItem(inventory);
                        BigDecimal prePurchaseMoney = currentMoneyProvided;
                        try{
                            if (itemSelection.getQuantity() > 0) {
                                currentMoneyProvided = dispense(itemSelection, currentMoneyProvided);
                                String prePurchaseFormat = "$" + prePurchaseMoney;
                                String currentMoneyFormat = "$" + currentMoneyProvided;
                                String writeAudit = String.format("%tD %tr %-18s %-3s %8s   %5s", LocalDate.now(), LocalTime.now().withNano(0),
                                        itemSelection.getName(),itemSelection.getSlotIdentifier(),prePurchaseFormat, currentMoneyFormat);
                                logger.write(writeAudit);
                            }
                        } catch (NullPointerException e){
                            System.out.println(" *** Invalid Selection ***");
                        }
                    } else if (purchaseChoice.equals("finish transaction")){
                        returnChange(currentMoneyProvided);
                        String currentMoneyFormat = "$" + currentMoneyProvided;
                        String writeAudit = String.format("%tD %tr %-18s %-3s %8s %5s", LocalDate.now(), LocalTime.now().withNano(0),
                                "CHANGE GIVEN", " ", currentMoneyFormat, "  $0.00");
                        logger.write(writeAudit);
                        currentMoneyProvided = new BigDecimal("0.00");
                        break;
                    }
                }
            }
            else if(choice.equals("exit")) {
                System.out.println("\nThank you for shopping with us!");
                break;
            }
        }
    }

    public BigDecimal dispense(Item itemSelection, BigDecimal currentMoneyProvided){
        BigDecimal itemPrice = itemSelection.getPrice();
        if (currentMoneyProvided.compareTo(itemPrice) == -1){
            System.out.println("\n *** Insufficient funds ***\n");
        } else {
            currentMoneyProvided = currentMoneyProvided.subtract(itemSelection.getPrice());
            itemSelection.reduceQuantity();
            System.out.println("------------------------------------------------------------------");
            System.out.println("Dispensing: " + itemSelection.getName() + "\nPrice: $" +
                    itemSelection.getPrice() + "\nRemaining Available Funds: $" + currentMoneyProvided);
            System.out.println(itemSelection.dispenseMessage());
            System.out.println("------------------------------------------------------------------");
        }
        return currentMoneyProvided;
    }

    public List<Item> readFile(String filePath) {
        File file = new File(filePath);
        List<Item> inventory = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineArray = line.split(",");
                if (lineArray[3].equals("Munchy")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Munchy(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Candy")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Candy(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Drink")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Drink(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Gum")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Gum(lineArray[0], lineArray[1], price));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return inventory;
    }

    @Override
    public void returnChange(BigDecimal currentMoneyProvided) {
        BigDecimal zero = new BigDecimal("0.00");
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (ChangeHelper.isChangeAvailable(currentMoneyProvided, dollar)) {
            currentMoneyProvided = currentMoneyProvided.subtract(dollar);
            dollars++;
        }
        while (ChangeHelper.isChangeAvailable(currentMoneyProvided, quarter)) {
            currentMoneyProvided = currentMoneyProvided.subtract(quarter);
            quarters++;
        }
        while (ChangeHelper.isChangeAvailable(currentMoneyProvided, dime)) {
            currentMoneyProvided = currentMoneyProvided.subtract(dime);
            dimes++;
        }
        while (ChangeHelper.isChangeAvailable(currentMoneyProvided, nickel)) {
            currentMoneyProvided = currentMoneyProvided.subtract(nickel);
            nickels++;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nReturning change: " + dollars + " dollar(s), " +
                quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
        System.out.println("------------------------------------------------------------------");
    }
}
