package com.example.demo.util;

public class ExceptionUtil {

    public static void isNotNull(Object value, String error) {
        if(value == null) {
            throw new RuntimeException(error);
        }
    }
}
