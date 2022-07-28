package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine implements Change {
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
                    } else if (purchaseChoice.equals("select item")) {
                        Item itemSelection = UserInput.selectItem(inventory);
                        try{
                            if (itemSelection.getQuantity() > 0) {
                                currentMoneyProvided = dispense(itemSelection, currentMoneyProvided);
                            }
                        } catch (NullPointerException e){
                            System.out.println(" *** Invalid Selection ***");
                        }
                    } else if (purchaseChoice.equals("finish transaction")){
                        returnChange(currentMoneyProvided);
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
                    itemSelection.getPrice() + "Remaining Available Funds: $" + currentMoneyProvided);
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

        while ((currentMoneyProvided.compareTo(dollar) == 1) ||
                currentMoneyProvided.compareTo(dollar) == 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(dollar);
            dollars++;
        }
        while ((currentMoneyProvided.compareTo(quarter) == 1) ||
                currentMoneyProvided.compareTo(quarter) == 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(quarter);
            quarters++;
        }
        while ((currentMoneyProvided.compareTo(dime) == 1) ||
                currentMoneyProvided.compareTo(dime) == 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(dime);
            dimes++;
        }
        while ((currentMoneyProvided.compareTo(nickel) == 1) ||
                currentMoneyProvided.compareTo(nickel) == 0) {
            currentMoneyProvided = currentMoneyProvided.subtract(nickel);
            nickels++;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nReturning change: " + dollars + " dollar(s), " +
                quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s).");
        System.out.println("------------------------------------------------------------------");
    }
}
