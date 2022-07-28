package com.techelevator.application;

import java.math.BigDecimal;

public interface Change {
    BigDecimal dollar = new BigDecimal("1.00");
    BigDecimal quarter = new BigDecimal("0.25");
    BigDecimal dime = new BigDecimal("0.10");
    BigDecimal nickel = new BigDecimal("0.05");

    public void returnChange (BigDecimal currentMoneyProvided);
}

