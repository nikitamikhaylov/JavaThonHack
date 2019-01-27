package ru.spulae.mulae;

public class Person {
    private int id;
    private double totalAmount;
    private String name;

    public Person(int id, String name, int totalAmount) {
        this.id = id;
        this.name = name;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setName(String name) {
        this.name = name;
    }
}
