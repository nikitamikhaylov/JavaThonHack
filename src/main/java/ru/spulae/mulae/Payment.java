package ru.spulae.mulae;

import java.util.Date;

public class Payment {
    private int amount;
    private String description;
    private Date datetime;

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Payment(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}
