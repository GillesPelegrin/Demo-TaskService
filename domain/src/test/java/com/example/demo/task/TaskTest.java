package com.example.demo.task;


import com.example.demo.util.DateTimeWrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.util.DateTimeWrapper.currentDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TaskTest {

    @AfterEach
    void tearDown() {
        DateTimeWrapper.reset();
    }

    @Test
    void Task_titleIsNull_exception() {
        assertThatThrownBy(() -> Task.builder().build())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("title is required in Task");
    }

    @Test
    void Task_messageIsNull_exception() {
        assertThatThrownBy(() -> Task.builder().title("title").build())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("message is required in Task");
    }

    @Test
    void Task_datesAreFilled() {
        DateTimeWrapper.pause();

        Task task = Task.builder()
                .title("title")
                .message("message")
                .build();

        assertThat(task.getCreationDate()).isEqualTo(currentDateTime());
        assertThat(task.getUpdateDate()).isEqualTo(currentDateTime());
    }

    @Test
    void Task() {
        assertDoesNotThrow(() -> Task.builder()
                .title("title")
                .message("message")
                .build());
    }
}