package com.example.demo.task;

import com.example.demo.task.Task;
import com.example.demo.task.TaskRepository;
import com.example.demo.testcontainer.PostgresTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static com.example.demo.testconstant.TaskTestConstant.getTaskBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class TaskRepositoryIntegrationTest {


    PostgresTestContainer a = new PostgresTestContainer();

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSave() {
        Task firstTask = getTask();
        Task secondTask = getTask();
        Task firstSavedTask = taskRepository.save(firstTask);
        Task secondSavedTask = taskRepository.save(secondTask);

        Page<Task> tasks = taskRepository.getTasks(Pageable.ofSize(5));

        assertThat(tasks.getTotalElements()).isEqualTo(2);
        assertThat(tasks.getTotalPages()).isEqualTo(1);
        assertThat(tasks.getNumber()).isEqualTo(0);

        assertThat(tasks.get().toList().get(0)).isEqualTo(firstSavedTask);
        assertThat(tasks.get().toList().get(1)).isEqualTo(secondSavedTask);
    }

    Task getTask() {
        return getTaskBuilder().id(UUID.randomUUID().toString()).build();
    }

}