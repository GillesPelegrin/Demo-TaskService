package com.example.demo;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.GetTasks200ResponseDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import com.example.demo.task.Task;
import com.example.demo.task.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
@Component
public class TaskApplicationService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public void createTask(CreateTaskDto taskDTO) {
        taskRepository.save(taskMapper.map(taskDTO));
    }

    public void updateTask(UpdateTaskDto taskDTO) {
        taskRepository.save(taskMapper.map(taskDTO));
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskDto getTaskById(String taskId) {
        return taskMapper.map(taskRepository.getReferenceById(taskId));
    }

    public GetTasks200ResponseDto getTasks(int page, int size) {
        Page<Task> tasks = taskRepository.getTasks(PageRequest.of(page, size));

        GetTasks200ResponseDto tasksDTO = new GetTasks200ResponseDto();
        tasksDTO.setItems(tasks.getContent().stream().map(taskMapper::map).toList());
        tasksDTO.setPageNumber(tasks.getNumber());
        tasksDTO.setTotalAmount((int) tasks.getTotalElements());
        return tasksDTO;
    }
}
