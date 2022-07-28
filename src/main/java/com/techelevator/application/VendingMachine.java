package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public void run() {
        List<Item> inventory = readFile("catering1.csv");
        //System.out.println(inventory);
        while(true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();
            System.out.println(choice);
            if(choice.equals("display")) {
                UserOutput.displayInventory(inventory);
            }
            else if(choice.equals("purchase")) {
                String purchaseChoice = UserInput.getPurchaseScreenOption();
                if (purchaseChoice.equals("feed money")) {

                }
            }
            else if(choice.equals("exit")) {
                // good bye
                break;
            }
        }
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
}
