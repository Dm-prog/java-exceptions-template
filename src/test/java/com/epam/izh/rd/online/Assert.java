package com.epam.izh.rd.online;

import lombok.SneakyThrows;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Assert<T extends Throwable> {

    public T assertThrowsWithClassName(String className, Executable executable, String message) {
        try {
            return assertThrows(forName(className), executable, message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    private Class<T> forName(String className) throws ClassNotFoundException {
        return (Class<T>) Class.forName("com.epam.izh.rd.online.exception." + className);
    }
}
