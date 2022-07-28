package models;

import java.math.BigDecimal;

public class Candy extends Item{

    public Candy(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, 6);
    }

    @Override
    public String dispenseMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
