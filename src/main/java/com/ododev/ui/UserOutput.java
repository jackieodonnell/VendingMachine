package com.ododev.ui;

import com.ododev.models.Item;

import java.util.List;
import java.util.Map;

public class UserOutput {
    public static void displayMessage(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen() {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayPurchaseScreen() {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                   Purchase Menu");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayInventory(List<Item> inventory) {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Inventory");
        System.out.println("***************************************************");
        System.out.println();

        for (Item item : inventory) {
            System.out.printf("%-5s %-20s %-8s %-2s", item.getSlotIdentifier(),
                    item.getName(), item.getPrice(), item.getQuantity());
            System.out.println();
        }
    }

    public static void displayReturnChange(Map<String, Integer> change){
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nReturning change: " + change.get("dollars") + " dollar(s), " +
                change.get("quarters") + " quarter(s), " + change.get("dimes") + " dime(s), and " +
                change.get("nickels") + " nickel(s).");
        System.out.println("------------------------------------------------------------------");
    }

}
