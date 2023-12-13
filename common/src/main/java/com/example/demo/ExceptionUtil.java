package com.example.demo;

import com.example.demo.exception.NotFoundException;

public class ExceptionUtil {

    public static void isNotNull(Object value, String error) {
        if (value == null) {
            throw new NotFoundException(error);
        }
    }
}
