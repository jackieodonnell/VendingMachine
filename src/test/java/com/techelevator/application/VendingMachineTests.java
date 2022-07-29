package com.techelevator.application;

import com.techelevator.models.Drink;
import com.techelevator.models.Item;
import com.techelevator.models.Munchy;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTests {

    @Test
    public void dispense_pass_in_A4_10_returns_6_15(){
        Munchy test1 = new Munchy("A4", "Chippos", new BigDecimal("3.85"));
        BigDecimal test2 = new BigDecimal("10.00");
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.dispense(test1,test2);
        BigDecimal expected = new BigDecimal("6.15");
        assertEquals(expected, actual);
    }

    @Test
    public void dispense_pass_in_b2_5_returns_1_55(){
        Drink test1 = new Drink("B2", "Papsi", new BigDecimal("3.45"));
        BigDecimal test2 = new BigDecimal("5.00");
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.dispense(test1,test2);
        BigDecimal expected = new BigDecimal("1.55");
        assertEquals(expected, actual);
    }

    @Test
    public void dispense_pass_in_B2_1_returns_1(){
        Drink test1 = new Drink("B2", "Papsi", new BigDecimal("3.45"));
        BigDecimal test2 = new BigDecimal("1.00");
        VendingMachine vendingMachine = new VendingMachine();
        BigDecimal actual = vendingMachine.dispense(test1,test2);
        BigDecimal expected = new BigDecimal("1.00");
        assertEquals(expected, actual);
    }
}