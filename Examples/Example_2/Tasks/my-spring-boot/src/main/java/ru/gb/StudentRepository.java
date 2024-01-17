package ru.gb;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component // studentRepository
public class StudentRepository {

    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("Student1", "group1"));
        students.add(new Student("Student2", "group1"));
        students.add(new Student("Student3", "group1"));
        students.add(new Student("Student4", "group2"));
        students.add(new Student("Student5", "group2"));
        students.add(new Student("Student6", "group2"));
        students.add(new Student("Student7", "group3"));
        students.add(new Student("Student8", "group3"));
        students.add(new Student("Student9", "group3"));
    }

    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public Student getById(long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Student getByName(String name) {
        return students.stream()
                .filter(it -> Objects.equals(it.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getByGroup(String groupName) {
        List<Student> groupList;
        groupList = students.stream()
                .filter(it -> Objects.equals(it.getGroupName(), groupName)).toList();
        return groupList;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudentById(long id) {
        students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(students::remove);
    }
}
