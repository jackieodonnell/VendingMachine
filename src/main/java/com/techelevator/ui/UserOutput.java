package com.techelevator.ui;

import models.Item;

import java.util.List;

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
}
