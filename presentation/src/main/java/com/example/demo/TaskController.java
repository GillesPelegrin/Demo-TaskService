package com.example.demo;

import com.example.demo.gen.springbootserver.api.V1Api;
import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.GetTasks200ResponseDto;
import com.example.demo.gen.springbootserver.model.PaginationDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController implements V1Api {

    private final TaskApplicationService taskApplicationService;

    @Override
    public ResponseEntity<Void> createTask(CreateTaskDto createTaskDto) {
        taskApplicationService.createTask(createTaskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteById(String taskId) {
        taskApplicationService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(String taskId) {
        return new ResponseEntity<>(taskApplicationService.getTaskById(taskId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetTasks200ResponseDto> getTasks() {
        return new ResponseEntity<>(taskApplicationService.getTasks(0,5), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateTask(UpdateTaskDto updateTaskDto) {
        taskApplicationService.updateTask(updateTaskDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
