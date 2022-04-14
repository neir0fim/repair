package com.kuzin.repair.service.addition;



public class Validation {

    public static void validateString(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }


}
