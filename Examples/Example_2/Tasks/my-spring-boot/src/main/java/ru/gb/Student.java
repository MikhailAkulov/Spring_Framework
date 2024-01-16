package ru.gb;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

@Data
public class Student {

    private static long idCounter = 1L;

    private final long id;
    private String name;

    @JsonCreator
    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String name) {
        this.id = idCounter++;
        this.name = name;
    }
}
