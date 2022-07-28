package models;

import java.math.BigDecimal;

public class Drink extends Item{
    public Drink(String slotIdentifier, String name, BigDecimal price) {
        super(slotIdentifier, name, price, 6);
    }

    @Override
    public String dispenseMessage() {
        return "Drinky, Drinky, Slurp Slurp!"
                ;
    }
}
