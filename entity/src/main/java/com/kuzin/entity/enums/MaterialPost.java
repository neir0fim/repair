package com.kuzin.entity.enums;


/**DTO class.*/
public class MaterialPost {
    long code;
    long repairId;
    long amount;

    public MaterialPost() {

    }

    public MaterialPost(long code, long repairId, long amount) {
        this.code = code;
        this.repairId = repairId;
        this.amount = amount;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
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
}
