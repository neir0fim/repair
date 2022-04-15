package com.kuzin.repair.entity;

public class WorksMaterial extends Material {
    long id;
    double amount;
    double cost;

    public WorksMaterial(int cod, String name, String codDk, String uom, double value, double amount) {
        super(cod, name, codDk, uom, value);
        this.amount = amount;
        cost = value * amount;
    }

    public WorksMaterial() {

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
