package com.example.demo;

import com.example.demo.domain.Task;
import com.example.demo.dto.CreateTaskDTO;
import com.example.demo.dto.TaskDTO;
import com.example.demo.dto.UpdateTaskDTO;

import java.time.LocalDateTime;

import static com.example.demo.util.DateTimeWrapper.currentDateTime;

public class TaskTestConstant {

    public static Task getTask() {
       return getTaskBuilder().build();
    }

    public static Task.TaskBuilder getTaskBuilder() {
        return Task.builder()
                .id(1L)
                .title("title")
                .message("message")
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now());
    }

    public static CreateTaskDTO getCreateTaskDTO() {
        return CreateTaskDTO.builder()
                .title("title")
                .message("message")
                .build();
    }

    public static UpdateTaskDTO getUpdateTaskDTO() {
        return UpdateTaskDTO.builder()
                .id(1L)
                .title("title")
                .message("message")
                .build();
    }

    public static TaskDTO getTaskDTO() {
        return TaskDTO.builder()
                .id(1L)
                .title("title")
                .message("message")
                .creationDate(currentDateTime())
                .updateDate(currentDateTime())
                .build();
    }
}
