package com.kuzin.entity;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "WorksMaterial{"
                + "cod=" + cod
                + ", name='" + name + '\''
                + ", codDk='" + codDk + '\''
                + ", uom='" + uom + '\''
                + ", value=" + value
                + ", id=" + id
                + ", amount=" + amount
                + ", cost=" + cost
                + ", repairId=" + repairId
                + '}';
    }


    @SuppressWarnings("checkstyle:NeedBraces")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WorksMaterial that = (WorksMaterial) o;
        return id == that.id && Double.compare(that.amount, amount) == 0
                && Double.compare(that.cost, cost) == 0 && repairId == that.repairId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, cost, repairId);
    }
}
