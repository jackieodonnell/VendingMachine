package com.techelevator.models;

import java.lang.invoke.StringConcatException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Item {

    private String slotIdentifier;
    private String name;
    private BigDecimal price;
    private int quantity = 6;
    private static Set<String> slotIdentifierSet = new HashSet<String>(Arrays.asList(
            "A1","A2","A3","A4","B1","B2","B3","B4","C1","C2","C3","C4","D1",
            "D2","D3","D4"));

    public Item(String slotIdentifier, String name, BigDecimal price, int quantity) {
        this.slotIdentifier = slotIdentifier;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static Set<String> getSlotIdentifierSet() {
        return slotIdentifierSet;
    }

    public void reduceQuantity() {
        this.quantity -= 1;
    }

    public abstract String dispenseMessage();
}

