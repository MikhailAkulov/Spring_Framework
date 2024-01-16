package ru.gb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/students")
//@RequiredArgsConstructor // если конструктор один, то очень упрощает работу
public class StudentController {

    // REST
    // GET /students/{id} => 404 (Not Found)
    // GET /students?nameLike='Igor%' => 204 (No Content)

    // DELETE /students/{id} - удаляет
    // POST   /students/      {"id": "1", "name": "newName"} - создает
    // PUT    /students/{id}  {"id": "1", "name": "newName"} - изменение

    // HTTP HyperText Transfer Protocol
    // GET POST PUT PATCH DELETE ... HEAD OPTIONS

    // http://ip-address/students/all -> List<Students>
    // http://ip-address/students/1 -> Student(1, Student #1)

//    @Autowired // 3-й способ через анностацию (без конструктора и сеттеров, спринг всё сделает сам)
    private final StudentRepository repository;

    // Внедрение через сеттеры
//    @Autowired
//    public void setRepository(StudentRepository repository) {
//        this.repository = repository;
//    }

    // Внедрение через конструктор (оптимальный способ)
    @Autowired
    public StudentController(StudentRepository repository) {
//        repository = new StudentRepository();
        this.repository = repository;
    }

    /**
     * http://ip-address/students/all -> List<Students>
     */
//    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @GetMapping(path = "all")
//    @GetMapping(path = "students") // то же что и @RequestMapping, но записывается короче
//    @ResponseBody // аннотация необходима, если у класса указана аннотация @Controller
    public List<Student> getStudents() {
//        return List.of(new Student("Student #1"), new Student("unknown"));
        return repository.getAll();
    }

    /**
     * http://ip-address/students/1 -> Student(1, Student #1)
     */
    @GetMapping("/{id}")
//    public Student getStudent(@PathVariable("id") long studentId) {
    public Student getStudent(@PathVariable long id) {
        return repository.getById(id);
    }

    /**
     * http://ip-address/students?name = Student #1 -> Student(1, Student #1)
     */
    @GetMapping
    public Student getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent (@PathVariable long id, @RequestBody Student student) {
        Student existStudent = repository.getById(id);  // так делать нехорошо
        if (existStudent == null) { //404
            throw new IllegalArgumentException();
        }
        existStudent.setName(student.getName());
        return existStudent;
    }   // http://localhost:8180/students/students/1  - запрос в postman (raw/Json)
        // http://localhost:8180/students/1 - проверка в браузере, что запрос сработал
}
