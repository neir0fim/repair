package com.kuzin.entity;

/**entity class.*/
public class WorksMaterial extends Material {
    long id;
    double amount;
    double cost;
    long repairId;

    public WorksMaterial(int cod, String name, String codDk, String uom, double value,
                         double amount, long repairId) {
        super(cod, name, codDk, uom, value);
        this.amount = amount;
        cost = value * amount;
        this.repairId = repairId;
    }

    public WorksMaterial(Material material) {
        super(material.cod, material.name, material.getCodDk(),
                material.uom, material.value);
    }

    public WorksMaterial(Material material, double amount) {
        super(material.cod, material.name, material.getCodDk(),
                material.uom, material.value);
        this.amount = amount;
        cost = value * amount;
    }

    public WorksMaterial() {

    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
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
