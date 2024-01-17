package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 3. Создать контроллер, обрабатывающий входящие запросы:
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    /**
     * 3.2 GET /student - получить всех студентов
     * http://localhost:8180/student
     */
    @GetMapping()
    public List<Student> getStudents() {
        return repository.getAll();
    }

    /**
     * 3.1 GET /student/{id} - получить студента по ID
     * http://localhost:8180/student/8
     */
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        return repository.getById(id);
    }

    /**
     * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
     * http://localhost:8180/student/search?name=Student7
     */
    @GetMapping("/search")
    public Student getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    /**
     * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
     * http://localhost:8180/student/add
     * {
     *     "id": "10",
     *     "name": "newStudent",
     *     "groupName": "group4"
     * }
     */
    @PostMapping("/add")
    public Student addNewStudent(@RequestBody Student student) {
        repository.addStudent(student);
        return student;
    }

    /**
     * 3.6 DELETE /student/{id} - удалить студента
     * http://localhost:8180/student/10
     */
    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable long id) {
        if (repository.getById(id) == null) {
            throw new IllegalArgumentException();
        }
        repository.deleteStudentById(id);
        return true;
    }
}
