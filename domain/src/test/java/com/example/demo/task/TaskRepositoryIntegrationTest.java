package com.example.demo.task;

import com.example.demo.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static com.example.demo.testconstant.TaskTestConstant.getTaskBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TaskRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void getTasks() {
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