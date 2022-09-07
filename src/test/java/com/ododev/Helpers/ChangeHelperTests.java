package com.ododev.Helpers;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ChangeHelperTests {

    @Test
    public void returnChange_pass_in_7_15_returns_7_dollars_1_dime_1_nickel() {
        BigDecimal testOne = new BigDecimal("7.15");
        Map<String, Integer> expected = new HashMap<>();
        expected.put("dollars", 7);
        expected.put("quarters", 0);
        expected.put("dimes", 1);
        expected.put("nickels",1);
        Map<String, Integer> actual = ChangeHelper.returnChange(testOne);
        expected.equals(actual);
    }

    @Test
    public void returnChange_pass_in_0_80_returns_0_dollars_3_quarters_1_nickel() {
        BigDecimal testOne = new BigDecimal("0.80");
        Map<String, Integer> expected = new HashMap<>();
        expected.put("dollars", 0);
        expected.put("quarters", 3);
        expected.put("dimes", 0);
        expected.put("nickels",1);
        Map<String, Integer> actual = ChangeHelper.returnChange(testOne);
        expected.equals(actual);
    }

    @Test
    public void returnChange_pass_in_0_returns_0() {
        BigDecimal testOne = new BigDecimal("0.00");
        Map<String, Integer> expected = new HashMap<>();
        expected.put("dollars", 0);
        expected.put("quarters", 0);
        expected.put("dimes", 0);
        expected.put("nickels",0);
        Map<String, Integer> actual = ChangeHelper.returnChange(testOne);
        expected.equals(actual);
    }
}