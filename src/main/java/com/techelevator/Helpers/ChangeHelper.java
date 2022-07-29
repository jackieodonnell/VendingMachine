package com.techelevator.Helpers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.techelevator.application.Change.dollar;

public class ChangeHelper {

    private static BigDecimal dollar = new BigDecimal("1.00");
    private static BigDecimal quarter = new BigDecimal("0.25");
    private static BigDecimal dime = new BigDecimal("0.10");
    private static BigDecimal nickel = new BigDecimal("0.05");

    public static Map<String, Integer> returnChange(BigDecimal currentMoneyProvided) {
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


    public static boolean isChangeAvailable(BigDecimal currentMoneyProvided, BigDecimal changeType) {
        return (currentMoneyProvided.compareTo(changeType) == 1 ||
                currentMoneyProvided.compareTo(changeType) == 0);
    }
}
