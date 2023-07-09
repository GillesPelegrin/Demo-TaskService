package com.example.demo.acceptancetest;

import com.example.demo.acceptancetest.client.TaskTestClient;
import org.junit.jupiter.api.Test;

import static com.example.demo.TaskTestConstant.getCreateTaskDTO;

public class TaskAcceptanceTest extends AbstractAcceptanceTest {

    private TaskTestClient taskTestClient;

    @Override
    protected void init() {
        taskTestClient = new TaskTestClient(mockMvc);
    }

    @Test
    void createUpdateAndDeleteTask() throws Exception {

        taskTestClient.createTask(getCreateTaskDTO());

//        taskTestClient.getTaskById(1L);


    }
}
