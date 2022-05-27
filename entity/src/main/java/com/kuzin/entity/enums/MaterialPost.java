package com.kuzin.entity.enums;


import java.util.Objects;

/**DTO class.*/
public class MaterialPost {
    int code;
    long repairId;
    long amount;

    public MaterialPost() {

    }

    public MaterialPost(int code, long repairId, long amount) {
        this.code = code;
        this.repairId = repairId;
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaterialPost that = (MaterialPost) o;
        return code == that.code && repairId == that.repairId && amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, repairId, amount);
    }
}
