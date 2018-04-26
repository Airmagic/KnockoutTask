package com.clara.taskdb.controller;

import com.clara.taskdb.model.Task;
import com.clara.taskdb.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskAPIController {

    private final TaskRepository tasks;

    @Autowired
    public TaskAPIController(TaskRepository tasks){
        this.tasks = tasks;

        tasks.save(new Task("task 1", true, false));
        tasks.save(new Task("task 2", false, true));
        tasks.save(new Task("task 3", true, false));
        tasks.save(new Task("task 4", false, true));
    }

    @PostMapping(value = "/add")
    public ResponseEntity addTask(@RequestBody Task task){
        System.out.println("new task "+ task);
        try {
            tasks.save(task);
        }catch (Exception e){
            return new ResponseEntity<>("Task object is invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> queryTask() {
        return new ResponseEntity<>(tasks.findAllByOrderByUrgentDesc(), HttpStatus.OK);
    }

    @PatchMapping(value="completed")
    public ResponseEntity markTaskAsCompleted(@RequestBody Task task){

        int tasksUpdated = tasks.setTaskCompleted(task.isCompleted(), task.getId());
        if (tasksUpdated == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value="urgent")
    public ResponseEntity toggleTaskAsUrgent(@RequestBody Task task){
        int tasksUpdated = tasks.toogleUrgent(task.isUrgent(), task.getId());
        if (tasksUpdated == 0){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity deleteTast(@RequestBody Task task){
        tasks.delete(task);
        return new ResponseEntity(HttpStatus.OK);
    }
}