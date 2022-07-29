package com.techelevator.application;

import com.techelevator.logger.Logger;
import com.techelevator.models.*;
import com.techelevator.reader.FileRead;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


import com.techelevator.Helpers.ChangeHelper;

public class VendingMachine implements Change {
    List<Item> inventory;
    BigDecimal currentMoneyProvided = new BigDecimal("0.00");
    Logger logger;

    public VendingMachine() {
        logger = new Logger("Audit.txt");
    }

    public void run() {
        inventory = FileRead.readFile("catering2.txt");
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            System.out.println(choice);
            if (choice.equals("display")) {
                UserOutput.displayInventory(inventory);
            } else if (choice.equals("purchase")) {
                purchaseMenu();
            } else if (choice.equals("exit")) {
                System.out.println("\nThank you for shopping with us!");
                break;
            }
        }
    }

    public void purchaseMenu() {
        while (true) {
            UserOutput.displayPurchaseScreen();
            String purchaseChoice = UserInput.getPurchaseScreenOption(currentMoneyProvided);
            if (purchaseChoice.equals("feed money")) {
                BigDecimal moneyInserted = UserInput.feedMoney();
                currentMoneyProvided = currentMoneyProvided.add(moneyInserted);
                if (moneyInserted.compareTo(new BigDecimal(0.00)) == 1) {
                    String moneyInsertedFormat = "$" + moneyInserted + ".00";
                    String currentMoneyProvidedFormat = "$" + currentMoneyProvided;
                    String writeAudit = String.format("%tD %tr %-18s %-3s %8s   %5s", LocalDate.now(), LocalTime.now().withNano(0),
                            "MONEY FED:", " ", moneyInsertedFormat, currentMoneyProvidedFormat);
                    logger.write(writeAudit);
                }
            } else if (purchaseChoice.equals("select item")) {
                Item itemSelection = UserInput.selectItem(inventory);
                BigDecimal prePurchaseMoney = currentMoneyProvided;
                try {
                    if (itemSelection.getQuantity() > 0) {
                        currentMoneyProvided = dispense(itemSelection, currentMoneyProvided);
                        String prePurchaseFormat = "$" + prePurchaseMoney;
                        String currentMoneyFormat = "$" + currentMoneyProvided;
                        String writeAudit = String.format("%tD %tr %-18s %-3s %8s   %5s", LocalDate.now(), LocalTime.now().withNano(0),
                                itemSelection.getName(), itemSelection.getSlotIdentifier(), prePurchaseFormat, currentMoneyFormat);
                        logger.write(writeAudit);
                    }
                } catch (NullPointerException e) {
                    System.out.println(" *** Invalid Selection ***");
                }
            } else if (purchaseChoice.equals("finish transaction")) {
                Map<String, Integer> change = returnChange(currentMoneyProvided);
                UserOutput.displayReturnChange(change);
                String currentMoneyFormat = "$" + currentMoneyProvided;
                String writeAudit = String.format("%tD %tr %-18s %-3s %8s %5s", LocalDate.now(), LocalTime.now().withNano(0),
                        "CHANGE GIVEN", " ", currentMoneyFormat, "  $0.00");
                logger.write(writeAudit);
                currentMoneyProvided = new BigDecimal("0.00");
                break;
            }
        }
    }

    public BigDecimal dispense(Item itemSelection, BigDecimal currentMoneyProvided) {
        BigDecimal itemPrice = itemSelection.getPrice();
        if (currentMoneyProvided.compareTo(itemPrice) == -1) {
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

    @Override
    public Map<String, Integer> returnChange(BigDecimal currentMoneyProvided) {
        BigDecimal zero = new BigDecimal("0.00");
        Map<String, Integer> change = new HashMap<>();
        change.put("dollars", 0);
        change.put("quarters", 0);
        change.put("dimes", 0);
        change.put("nickels", 0);

        while (currentMoneyProvided.compareTo(zero) == 1) {
            if (ChangeHelper.isChangeAvailable(currentMoneyProvided, dollar)) {
                currentMoneyProvided = currentMoneyProvided.subtract(dollar);
                change.put("dollars", change.get("dollars") + 1);
                continue;
            } else if (ChangeHelper.isChangeAvailable(currentMoneyProvided, quarter)) {
                currentMoneyProvided = currentMoneyProvided.subtract(quarter);
                change.put("quarters", change.get("quarters") + 1);
                continue;
            } else if (ChangeHelper.isChangeAvailable(currentMoneyProvided, dime)) {
                currentMoneyProvided = currentMoneyProvided.subtract(dime);
                change.put("dimes", change.get("dimes") + 1);
                continue;
            } else if (ChangeHelper.isChangeAvailable(currentMoneyProvided, nickel)) {
                currentMoneyProvided = currentMoneyProvided.subtract(nickel);
                change.put("nickels", change.get("nickels") + 1);
                continue;
            }
        }
        return change;
    }
}
