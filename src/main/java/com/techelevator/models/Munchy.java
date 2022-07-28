package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends Item{

    public Munchy(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, 6);
    }

    @Override
    public String dispenseMessage() {
        return "Munchy, Munchy, so Good!";
    }
}
