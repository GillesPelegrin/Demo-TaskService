package com.example.demo;

import com.example.demo.task.Task;
import com.example.demo.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.demo.TaskDtoTestConstant.getCreateTaskDTO;
import static com.example.demo.TaskDtoTestConstant.getUpdateTaskDTO;
import static com.example.demo.testconstant.TaskTestConstant.TASK_ID;
import static com.example.demo.testconstant.TaskTestConstant.getTask;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskApplicationServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskApplicationService taskApplicationService;

    @Test
    void createTask() {
        Task task = getTask();
        when(taskMapper.map(getCreateTaskDTO())).thenReturn(task);
        taskApplicationService.createTask(getCreateTaskDTO());
        verify(taskRepository).save(task);
    }

    @Test
    void updateTask() {
        Task task = getTask();
        when(taskMapper.map(getUpdateTaskDTO())).thenReturn(task);
        taskApplicationService.updateTask(getUpdateTaskDTO());
        verify(taskRepository).save(task);
    }

    @Test
    void deleteTask() {
        taskApplicationService.deleteTask(TASK_ID);
        verify(taskRepository).deleteById(TASK_ID);
    }

    @Test
    void getTaskById() {
        taskApplicationService.getTaskById(TASK_ID);
        verify(taskRepository).getReferenceById(TASK_ID);
    }
}