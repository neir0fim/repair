package com.kuzin.service.service.addition;

import java.util.List;
import java.util.NoSuchElementException;


public class Validation {

    private Validation() {

    }

    public static void validateString(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateList(List<?> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
    }


}
