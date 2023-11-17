package com.example.demo;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import com.example.demo.task.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task map(CreateTaskDto taskDTO) {
        return Task.builder()
                .message(taskDTO.getMessage())
                .title(taskDTO.getTitle())
                .build();
    }

    public Task map(UpdateTaskDto taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .message(taskDTO.getMessage())
                .title(taskDTO.getTitle())
                .build();
    }

    public TaskDto map(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setMessage(task.getMessage());
        taskDto.setTitle(task.getTitle());
        taskDto.setCreationDate(task.getCreationDate().toString());
        taskDto.setUpdateDate(task.getUpdateDate().toString());
        return taskDto;
    }
}
