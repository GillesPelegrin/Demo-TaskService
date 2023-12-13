package com.example.demo.controller;

import com.example.demo.TaskApplicationService;
import com.example.demo.gen.springbootserver.api.TaskApi;
import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.GetTasks200ResponseDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import com.example.demo.security.TokenVerify;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController implements TaskApi {

    private final TaskApplicationService taskApplicationService;

    @TokenVerify
    @Override
    public ResponseEntity<Void> createTask(CreateTaskDto createTaskDto) {
        taskApplicationService.createTask(createTaskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @TokenVerify
    @Override
    public ResponseEntity<Void> deleteById(String taskId) {
        taskApplicationService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @TokenVerify
    @Override
    public ResponseEntity<TaskDto> getTaskById(String taskId) {
        return new ResponseEntity<>(taskApplicationService.getTaskById(taskId), HttpStatus.OK);
    }

    @TokenVerify
    @Override
    public ResponseEntity<GetTasks200ResponseDto> getTasks() {
        return new ResponseEntity<>(taskApplicationService.getTasks(0, 5), HttpStatus.OK);
    }

    @TokenVerify
    @Override
    public ResponseEntity<Void> updateTask(UpdateTaskDto updateTaskDto) {
        taskApplicationService.updateTask(updateTaskDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
