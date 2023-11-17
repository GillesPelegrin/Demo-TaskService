package com.example.demo.acceptancetest;

import com.example.demo.acceptancetest.client.TaskTestClient;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import static com.example.demo.TaskDtoTestConstant.getCreateTaskDTO;
import static com.example.demo.TaskDtoTestConstant.getTaskDTO;
import static com.example.demo.TaskDtoTestConstant.getUpdateTaskDTO;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskAcceptanceTest extends AbstractAcceptanceTest {


    @Test
    void createUpdateAndDeleteTask() {
        TaskTestClient taskTestClient = new TaskTestClient(getMockMvc());

        taskTestClient.createTask(getCreateTaskDTO());

        var tasks = taskTestClient.getTasks(Pageable.ofSize(5));
        String taskId = tasks.getItems().get(0).getId();

        assertThat(tasks.getPageNumber()).isEqualTo(0);
        assertThat(tasks.getTotalAmount()).isEqualTo(1);
        assertThat(tasks.getItems().size()).isEqualTo(1);
        assertThat(tasks.getItems().get(0))
                .usingRecursiveComparison()
                .ignoringFields("id", "creationDate", "updateDate")
                .isEqualTo(getTaskDTO());


        UpdateTaskDto updateTaskDTO = getUpdateTaskDTO();
        updateTaskDTO.setId(taskId);

        taskTestClient.updateTask(updateTaskDTO);
        TaskDto updatedTask = taskTestClient.getTaskById(taskId);

        assertThat(updatedTask)
                .usingRecursiveComparison()
                .ignoringFields("creationDate", "updateDate")
                .isEqualTo(updateTaskDTO);

        taskTestClient.deleteTask(taskId);

        var deletedTasks = taskTestClient.getTasks(Pageable.ofSize(5));

        assertThat(deletedTasks.getPageNumber()).isEqualTo(0);
        assertThat(deletedTasks.getTotalAmount()).isEqualTo(0);
        assertThat(deletedTasks.getItems().size()).isEqualTo(0);
    }
}
