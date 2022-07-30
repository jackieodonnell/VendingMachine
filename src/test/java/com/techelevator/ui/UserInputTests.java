package com.techelevator.ui;

import com.techelevator.models.Item;
import com.techelevator.reader.FileRead;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class UserInputTests {

    @Test
    public void getPurchaseScreenOption_pass_in_m_returns_feed_money() {

    }
    InputStream inputStream;

    @Test
    public void feedMoney_pass_in_5_returns_5() {
        String testOne = "5";
        inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        BigDecimal actual = UserInput.feedMoney();
        BigDecimal expected = new BigDecimal("5") ;

        assertEquals(expected, actual);
    }

    @Test
    public void feedMoney_pass_in_50_returns_0() {
        String testOne = "50";
        InputStream inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        BigDecimal actual = UserInput.feedMoney();
        BigDecimal expected = new BigDecimal("0.00") ;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void feedMoney_pass_in_wasd_returns_0() {
        String testOne = "wasd";
        InputStream inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        BigDecimal actual = UserInput.feedMoney();
        BigDecimal expected = new BigDecimal("0.00") ;

        Assert.assertEquals(expected, actual);
    }



    @Test
    public void selectItem_pass_in_A4_returns_Chippos() {
        String testOne = "A4";
        List<Item> inventory = FileRead.readFile("catering2.txt");
        InputStream inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        Item actual = UserInput.selectItem(inventory);
        Item expected = inventory.get(3);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void selectItem_pass_in_b4_returns_Wonka_Bar() {
        String testOne = "b4";
        List<Item> inventory = FileRead.readFile("catering2.txt");
        InputStream inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        Item actual = UserInput.selectItem(inventory);
        Item expected = inventory.get(7);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void selectItem_pass_in_wasd_return_null() {
        String testOne = "wasd";
        List<Item> inventory = FileRead.readFile("catering2.txt");
        InputStream inputStream = new ByteArrayInputStream(testOne.getBytes());
        System.setIn(inputStream);
        Item actual = UserInput.selectItem(inventory);

        assertNull(actual);
    }
}