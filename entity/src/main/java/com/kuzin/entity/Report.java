package com.kuzin.entity;

import java.util.List;

public class Report {
    int success;
    int fail;
    List<Integer> failRow;

    public Report(int success, int fail, List<Integer> failRow) {
        this.success = success;
        this.fail = fail;
        this.failRow = failRow;
    }


    public int getSuccess() {
        return success;
    }

    public int getFail() {
        return fail;
    }

    public List<Integer> getFailRow() {
        return failRow;
    }
}
