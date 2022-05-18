package com.kuzin.service.service.addition;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**addition validation.*/
@Component
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

    public static void validateObject(Object ob) {
        if (Objects.isNull(ob)) {
            throw new NullPointerException();
        }
    }

    public static void validId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
    }


}
