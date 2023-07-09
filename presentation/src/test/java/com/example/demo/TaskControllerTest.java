package com.example.demo;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
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
        CreateTaskDTO createTaskDTO = Mockito.mock(CreateTaskDTO.class);
        taskController.createTask(createTaskDTO);

        verify(taskApplicationService).createTask(createTaskDTO);
    }

    @Test
    void updateTask() {
        UpdateTaskDTO updateTaskDTO = Mockito.mock(UpdateTaskDTO.class);
        taskController.updateTask(updateTaskDTO);

        verify(taskApplicationService).updateTask(updateTaskDTO);
    }

    @Test
    void deleteTask() {
        taskController.deleteTask(TASK_ID);
        verify(taskApplicationService).deleteTask(TASK_ID);
    }

    @Test
    void getTaskById() {
        taskController.getTaskById(TASK_ID);
        verify(taskApplicationService).getTaskById(TASK_ID);
    }
}