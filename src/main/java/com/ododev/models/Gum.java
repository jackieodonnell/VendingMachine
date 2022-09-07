package com.ododev.models;

import java.math.BigDecimal;

public class Gum extends Item{

    public Gum(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, 6);
    }

    @Override
    public String dispenseMessage() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }
}
