package com.example.demo.testconstant;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;

import static com.example.demo.testconstant.TaskTestConstant.TASK_ID;
import static com.example.demo.DateTimeWrapper.currentDateTime;


public class TaskDtoTestConstant {

    public static CreateTaskDto getCreateTaskDTO() {
        CreateTaskDto createTaskDto = new CreateTaskDto();
        createTaskDto.setTitle("title");
        createTaskDto.setMessage("message");
        return createTaskDto;
    }


    public static UpdateTaskDto getUpdateTaskDTO() {
        UpdateTaskDto updateTaskDto = new UpdateTaskDto();
        updateTaskDto.setId(TASK_ID);
        updateTaskDto.setTitle("title");
        updateTaskDto.setMessage("updatedMessage");
        return updateTaskDto;
    }

    public static TaskDto getTaskDTO() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(TASK_ID);
        taskDto.setTitle("title");
        taskDto.setMessage("message");
        taskDto.setCreationDate(currentDateTime().toString());
        taskDto.setUpdateDate(currentDateTime().toString());
        return taskDto;
    }
}
