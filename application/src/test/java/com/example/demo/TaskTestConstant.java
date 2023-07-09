package com.example.demo;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.UpdateTaskDTO;

import java.time.LocalDateTime;

import static com.example.demo.DateTimeWrapper.currentDateTime;


public class TaskTestConstant {

    public static String TASK_ID = "e282338f-3cec-426b-b36c-10a83d431682";

    public static Task getTask() {
        return getTaskBuilder().build();
    }

    public static Task.TaskBuilder getTaskBuilder() {
        return Task.builder()
                .id(TASK_ID)
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

    public static UpdateTaskDTO.UpdateTaskDTOBuilder getUpdateTaskDTOBuilder() {
        return UpdateTaskDTO.builder()
                .id(TASK_ID)
                .title("title")
                .message("updatedMessage");
    }

    public static UpdateTaskDTO getUpdateTaskDTO() {
        return getUpdateTaskDTOBuilder().build();
    }

    public static TaskDTO getTaskDTO() {
        return getTaskDTOBuilder().build();
    }

    public static TaskDTO.TaskDTOBuilder getTaskDTOBuilder() {
        return TaskDTO.builder()
                .id(TASK_ID)
                .title("title")
                .message("message")
                .creationDate(currentDateTime())
                .updateDate(currentDateTime());
    }
}
