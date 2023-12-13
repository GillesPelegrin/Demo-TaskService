package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileUtil {

    public static String readFile(Class referenceClass, String filePath) {
        InputStream inputStream = referenceClass.getResourceAsStream(filePath);
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }
}
