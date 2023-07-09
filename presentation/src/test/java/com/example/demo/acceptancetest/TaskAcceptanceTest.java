package com.example.demo.acceptancetest;

import com.example.demo.acceptancetest.client.TaskTestClient;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TasksDTO;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static com.example.demo.TaskTestConstant.getCreateTaskDTO;
import static com.example.demo.TaskTestConstant.getTaskDTO;
import static com.example.demo.TaskTestConstant.getTaskDTOBuilder;
import static com.example.demo.TaskTestConstant.getUpdateTaskDTOBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskAcceptanceTest extends AbstractAcceptanceTest {

    private TaskTestClient taskTestClient;

    @Override
    protected void init() {
        taskTestClient = new TaskTestClient(mockMvc);
    }

    @Test
    void createUpdateAndDeleteTask() throws Exception {

        taskTestClient.createTask(getCreateTaskDTO());

        TasksDTO tasks = taskTestClient.getTask(Pageable.ofSize(5));

        assertThat(tasks.getPageNumber()).isEqualTo(0);
        assertThat(tasks.getTotalAmount()).isEqualTo(1);
        assertThat(tasks.getItems().size()).isEqualTo(1);
        assertThat(tasks.getItems().get(0))
                .usingRecursiveComparison()
                .ignoringFields("id", "creationDate", "updateDate")
                .isEqualTo(getTaskDTO());

        String taskId = tasks.getItems().get(0).id();

        taskTestClient.updateTask(getUpdateTaskDTOBuilder().id(taskId).build());

        TaskDTO updatedTask = taskTestClient.getTaskById(taskId);

        assertThat(updatedTask)
                .usingRecursiveComparison()
                .ignoringFields("creationDate", "updateDate")
                .isEqualTo(getTaskDTOBuilder().id(taskId).message("updatedMessage").build());

        taskTestClient.deleteTask(taskId);

        TasksDTO deletedTasks = taskTestClient.getTask(Pageable.ofSize(5));

        assertThat(deletedTasks.getPageNumber()).isEqualTo(0);
        assertThat(deletedTasks.getTotalAmount()).isEqualTo(0);
        assertThat(deletedTasks.getItems().size()).isEqualTo(0);
    }
}
