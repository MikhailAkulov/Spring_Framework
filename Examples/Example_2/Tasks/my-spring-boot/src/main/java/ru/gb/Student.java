package ru.gb;

import lombok.Data;

@Data
public class Student {

    private static long idCounter = 1L;

    private final long id;
    private String name;

    public Student(String name) {
        this.id = idCounter++;
        this.name = name;
    }
}
