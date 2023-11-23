package com.example.demo.testconstant;

import com.example.demo.task.Task;
import com.example.demo.util.DateTimeWrapper;

import java.time.LocalDateTime;


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
                .creationDate(DateTimeWrapper.currentDateTime())
                .updateDate(DateTimeWrapper.currentDateTime());
    }
}
