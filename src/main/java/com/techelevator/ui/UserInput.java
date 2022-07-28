package com.techelevator.ui;

import java.math.BigDecimal;
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
        System.out.println("option = " + option);
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
    static BigDecimal currentMoneyProvided = new BigDecimal("0.00");
    public static String getPurchaseScreenOption() {
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
        System.out.println("option = " + purchaseOption);
        if (purchaseOption.equals("m")) {
            System.out.println("Insert Dollar Bill: (1, 5, 10,20)");
            BigDecimal moneyInserted = new BigDecimal(scanner.nextLine());
            currentMoneyProvided = currentMoneyProvided.add(moneyInserted);
            return currentMoneyProvided.toString();
        } else if (purchaseOption.equals("s")) {
            return "select item";
        } else if (purchaseOption.equals("f")) {
            return "finish transaction";
        } else {
            return "";
        }
    }
}

//        public static BigDecimal feedMoney() {
//          System.out.println("Insert Dollars: ($1) ($5) ($10) ($20)");
//            while (true) {
//                System.out.println("Insert Dollars: ($1) ($5) ($10) ($20)");
//               BigDecimal currentMoney = new BigDecimal(scanner.nextLine());
//
//            }
//        }
//
//
//    }





