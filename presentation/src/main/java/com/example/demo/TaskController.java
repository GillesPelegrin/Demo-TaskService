package com.example.demo;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TasksDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskApplicationService taskApplicationService;

    @PostMapping("/task")
    public ResponseEntity createTask(@RequestBody CreateTaskDTO taskDTO) {
        taskApplicationService.createTask(taskDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/task")
    public void updateTask(@RequestBody UpdateTaskDTO taskDTO) {
        taskApplicationService.updateTask(taskDTO);
    }

    @DeleteMapping("/task/{taskId}")
    public void deleteTask(@PathVariable String taskId) {
        taskApplicationService.deleteTask(taskId);
    }

    @GetMapping("/task/{taskId}")
    public TaskDTO getTaskById(@PathVariable String taskId) {
        return taskApplicationService.getTaskById(taskId);
    }

    @GetMapping("/task")
    public TasksDTO getTasks() {
        return taskApplicationService.getTasks();
    }

}
