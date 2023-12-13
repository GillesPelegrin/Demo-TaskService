package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static com.example.demo.testconstant.TaskDtoTestConstant.getCreateTaskDTO;
import static com.example.demo.testconstant.TaskDtoTestConstant.getTaskDTO;
import static com.example.demo.testconstant.TaskDtoTestConstant.getUpdateTaskDTO;
import static com.example.demo.testconstant.TaskTestConstant.getTask;
import static com.example.demo.testconstant.TaskTestConstant.getTaskBuilder;
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