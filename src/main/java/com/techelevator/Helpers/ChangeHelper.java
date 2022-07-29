package com.techelevator.Helpers;

import java.math.BigDecimal;

import static com.techelevator.application.Change.dollar;

public class ChangeHelper {

    public static boolean isChangeAvailable(BigDecimal currentMoneyProvided, BigDecimal changeType) {
        return (currentMoneyProvided.compareTo(changeType) == 1 ||
                currentMoneyProvided.compareTo(changeType) == 0);
    }
}
