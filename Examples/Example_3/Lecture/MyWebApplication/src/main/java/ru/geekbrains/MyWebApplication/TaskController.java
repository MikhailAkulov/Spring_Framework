package ru.geekbrains.MyWebApplication;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // http://localhost:8080/task
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    // http://localhost:8080/task
    // Body:
    // {
    //    "name": "сходить в магазин",
    //    "description": "до 20:00"
    // }
    //
    // {
    //    "name": "сходить в кино",
    //    "description": "до 21:00"
    // }
    //
    // {
    //    "name": "лечь спать",
    //    "description": "до 23:00"
    // }
    @PostMapping()
    public Task addNewTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Task updateById(@PathVariable UUID id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }
}
