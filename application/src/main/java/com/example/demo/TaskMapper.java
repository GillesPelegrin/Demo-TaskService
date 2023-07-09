package com.example.demo;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task map(CreateTaskDTO taskDTO) {
        return Task.builder()
                .message(taskDTO.message())
                .title(taskDTO.title())
                .build();
    }

    public Task map(UpdateTaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.id())
                .message(taskDTO.message())
                .title(taskDTO.title())
                .build();
    }

    public TaskDTO map(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .message(task.getMessage())
                .title(task.getTitle())
                .creationDate(task.getCreationDate())
                .updateDate(task.getUpdateDate())
                .build();
    }
}
