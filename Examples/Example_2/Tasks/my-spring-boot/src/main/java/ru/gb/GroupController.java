package ru.gb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final StudentRepository repository;

    @Autowired
    public GroupController(StudentRepository repository) {
        this.repository = repository;
    }

    /**
     * 3.4 GET /group/{groupName}/student - получить всех студентов группы
     * http://localhost:8180/group/group2/student
     */
    @GetMapping("/{groupName}/student")
    public List<Student> getByGroup(@PathVariable String groupName) {
        return repository.getByGroup(groupName);
    }
}
