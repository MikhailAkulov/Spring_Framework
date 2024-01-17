package ru.gb;

import lombok.Data;

/**
 * 2. Создать Класс Student c полями: идентификатор, имя, имя группы
 */
@Data
public class Student {

    private static long idCounter = 1L;

    private long id;
    private String name;
    private String groupName;

    public Student(long id, String name, String groupName) {
        this.id = id;
        this.name = name;
        this.groupName = groupName;
    }

    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
