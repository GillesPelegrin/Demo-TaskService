package com.example.demo;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TasksDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskDTO getTaskById(String taskId) {
        return taskMapper.map(taskRepository.getReferenceById(taskId));
    }

    public TasksDTO getTasks() {
        Page<Task> tasks = taskRepository.getTasks(Pageable.ofSize(5));

        return TasksDTO.builder()
                .pageNumber((long) tasks.getNumber())
                .items(tasks.getContent().stream().map(taskMapper::map).toList())
                .totalAmount(tasks.getTotalElements())
                .build();
    }
}
