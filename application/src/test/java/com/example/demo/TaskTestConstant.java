package com.example.demo;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;

import java.time.LocalDateTime;

import static com.example.demo.util.DateTimeWrapper.currentDateTime;


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

    public static CreateTaskDto getCreateTaskDTO() {
        CreateTaskDto createTaskDto = new CreateTaskDto();
        createTaskDto.setTitle("title");
        createTaskDto.setMessage("message");
        return createTaskDto;
    }


    public static UpdateTaskDto getUpdateTaskDTO() {
        UpdateTaskDto updateTaskDto = new UpdateTaskDto();
        updateTaskDto.setId(TASK_ID);
        updateTaskDto.setTitle("title");
        updateTaskDto.setMessage("updatedMessage");
        return updateTaskDto;
    }

    public static TaskDto getTaskDTO() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(TASK_ID);
        taskDto.setTitle("title");
        taskDto.setMessage("message");
        taskDto.setCreationDate(currentDateTime().toString());
        taskDto.setUpdateDate(currentDateTime().toString());
        return taskDto;
    }
}
