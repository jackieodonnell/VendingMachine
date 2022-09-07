package com.ododev.ui;

import com.ododev.models.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 *
 * Dependencies: None
 */
public class UserInput {
    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();
        if (option.equals("d")) {
            return "display";
        } else if (option.equals("p")) {
            return "purchase";
        } else if (option.equals("e")) {
            return "exit";
        } else {
            return "";
        }
    }

    public static String getPurchaseScreenOption(BigDecimal currentMoneyProvided) {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("M) Feed Money");
        System.out.println("S) Select Item");
        System.out.println("F) Finish Transaction");

        System.out.println("\nCurrent Money Provided: $" + currentMoneyProvided);
        System.out.println();
        System.out.print("Please select an option: ");

        String selectedPurchaseOption = scanner.nextLine();
        String purchaseOption = selectedPurchaseOption.trim().toLowerCase();
        if (purchaseOption.equals("m")) {
            return "feed money";
        } else if (purchaseOption.equals("s")) {
            return "select item";
        } else if (purchaseOption.equals("f")) {
            return "finish transaction";
        } else {
            return "";
        }
    }

    public static BigDecimal feedMoney() {
        System.out.print("\nInsert Dollar Bill: (1, 5, 10, 20) >>> ");
        BigDecimal moneyInserted = new BigDecimal("0.00");
        try {
            int moneyInt = Integer.parseInt(scanner.nextLine());
            if (moneyInt == 1 || moneyInt == 5 || moneyInt == 10 || moneyInt == 20) {
                moneyInserted = new BigDecimal(moneyInt);
            } else {
                System.out.println("\n*** Unsupported currency ***");
            }
        } catch (NumberFormatException | NoSuchElementException e){
            System.out.println("\n*** Unrecognized entry ***");
        }
        return moneyInserted;
    }

    public static Item selectItem(List<Item> inventory) {
        UserOutput.displayInventory(inventory);
        System.out.print("\nInput a selection: ");
        String inputCode = scanner.nextLine();
        Item selectedItem = null;
        for (Item item : inventory) {
            if (item.getSlotIdentifier().equals(inputCode.toUpperCase())) {
                selectedItem = item;
                if (selectedItem.getQuantity() == 0) {
                    System.out.println("NO LONGER AVAILABLE");
                }
            }
        }
        return selectedItem;
    }
}





