package com.example.demo.service;

import com.example.demo.dto.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.domain.TaskRepository;
import com.example.demo.dto.CreateTaskDTO;
import com.example.demo.dto.UpdateTaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
@Component
public class TaskApplicationService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public void createTask(CreateTaskDTO taskDTO) {
        taskRepository.save(taskMapper.map(taskDTO));
    }

    public void updateTask(UpdateTaskDTO taskDTO) {
        taskRepository.save(taskMapper.map(taskDTO));
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskDTO getTaskById(Long taskId) {
        return taskMapper.map(taskRepository.getReferenceById(taskId));
    }

//    public void getTasks() {
        // the idea is for returning pagination
//    }

}
