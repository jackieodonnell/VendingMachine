package models;

import java.lang.invoke.StringConcatException;
import java.math.BigDecimal;

public abstract class Item {

    private String slotIdentifier;
    private String name;
    private BigDecimal price;
    private int quantity = 6;


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

    public String getQuantity() {
        if (quantity == 0) {
            return "NO LONGER AVAILABLE";
        }
        return String.valueOf(quantity);
    }

    public abstract String dispenseMessage();
}

