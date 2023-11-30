package com.example.demo.controller;

import com.example.demo.TaskApplicationService;
import com.example.demo.controller.TaskController;
import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.GetTasks200ResponseDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.demo.testconstant.TaskTestConstant.TASK_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskApplicationService taskApplicationService;

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskDto taskDto;

    @Mock
    private GetTasks200ResponseDto getTasks200ResponseDto;


    @Test
    void createTask() {
        CreateTaskDto createTaskDTO = Mockito.mock(CreateTaskDto.class);
        taskController.createTask(createTaskDTO);

        verify(taskApplicationService).createTask(createTaskDTO);
    }

    @Test
    void updateTask() {
        UpdateTaskDto updateTaskDTO = Mockito.mock(UpdateTaskDto.class);
        taskController.updateTask(updateTaskDTO);

        verify(taskApplicationService).updateTask(updateTaskDTO);
    }

    @Test
    void deleteById() {
        taskController.deleteById(TASK_ID);
        verify(taskApplicationService).deleteTask(TASK_ID);
    }

    @Test
    void getTaskById() {
        when(taskApplicationService.getTaskById(TASK_ID)).thenReturn(taskDto);

        assertThat(taskController.getTaskById(TASK_ID))
                .isEqualTo(new ResponseEntity<>(taskDto, HttpStatus.OK));
    }

    @Test
    void getTasks() {
        when(taskApplicationService.getTasks(0, 5)).thenReturn(getTasks200ResponseDto);

        assertThat(taskController.getTasks())
                .isEqualTo(new ResponseEntity<>(getTasks200ResponseDto, HttpStatus.OK));
    }
}