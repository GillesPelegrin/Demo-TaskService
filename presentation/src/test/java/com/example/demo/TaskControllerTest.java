package com.example.demo;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.TaskTestConstant.TASK_ID;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskApplicationService taskApplicationService;

    @InjectMocks
    private TaskController taskController;

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
        taskController.getTaskById(TASK_ID);
        verify(taskApplicationService).getTaskById(TASK_ID);
    }
}