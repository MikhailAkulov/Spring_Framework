package ru.gb;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component // studentRepository - название по умолчанию (название класса с маленькой буквы) если название не задано
//@Primary
//@Component("myStudentRepositoryBean") // способ задать название бина
//@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class StudentRepository {

    private final List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
        students.add(new Student("Student1"));
        students.add(new Student("Student2"));
        students.add(new Student("Student3"));
        students.add(new Student("Student4"));
        students.add(new Student("Student5"));
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
}
