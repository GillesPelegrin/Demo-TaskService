package com.example.demo;

import org.junit.jupiter.api.BeforeAll;

import com.example.demo.util.DateTimeWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.example.demo.TaskTestConstant.getCreateTaskDTO;
import static com.example.demo.TaskTestConstant.getTask;
import static com.example.demo.TaskTestConstant.getTaskBuilder;
import static com.example.demo.TaskTestConstant.getTaskDTO;
import static com.example.demo.TaskTestConstant.getUpdateTaskDTO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaskMapperTest {

    @BeforeAll
    static void beforeAll() {
        DateTimeWrapper.setFixed(Instant.now());
    }

    @Test
    void map_createTaskDTO_to_task() {
        TaskMapper taskMapper = new TaskMapper();
        assertThat(taskMapper.map(getCreateTaskDTO())).usingRecursiveComparison()
                .ignoringFields("creationDate", "updateDate")
                .isEqualTo(getTaskBuilder().id(null).build());
    }

    @Test
    void map_updateTaskDTO_to_task() {
        TaskMapper taskMapper = new TaskMapper();
        assertThat(taskMapper.map(getUpdateTaskDTO())).usingRecursiveComparison()
                .ignoringFields("creationDate", "updateDate")
                .isEqualTo(getTaskBuilder().message("updatedMessage").build());
    }

    @Test
    void map_task_to_taskDTO() {
        TaskMapper taskMapper = new TaskMapper();
        assertThat(taskMapper.map(getTask())).usingRecursiveComparison()
                .isEqualTo(getTaskDTO());
    }
}