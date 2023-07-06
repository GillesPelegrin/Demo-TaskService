package com.example.demo.controller;

import com.example.demo.dto.CreateTaskDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UpdateTaskDTO;
import com.example.demo.service.TaskApplicationService;
import lombok.AllArgsConstructor;
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
    public void createTask(@RequestBody CreateTaskDTO taskDTO) {
        taskApplicationService.createTask(taskDTO);
    }

    @PutMapping("/task")
    public void updateTask(@RequestBody UpdateTaskDTO taskDTO) {
        taskApplicationService.updateTask(taskDTO);
    }

    @DeleteMapping("/task/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskApplicationService.deleteTask(taskId);
    }

    @GetMapping("/task/{taskId}")
    public TaskDTO getTaskById(@PathVariable Long taskId) {
        return taskApplicationService.getTaskById(taskId);
    }

}
