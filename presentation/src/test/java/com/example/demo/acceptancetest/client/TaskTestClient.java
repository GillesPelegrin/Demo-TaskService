package com.example.demo.acceptancetest.client;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TasksDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TaskTestClient extends TestClient {

    public TaskTestClient(MockMvc mockMvc) {
        super(mockMvc);
    }

    public void createTask(CreateTaskDTO taskDTO) {
        post(taskDTO, "http://loclhost:8080/task");
    }

    public void updateTask(UpdateTaskDTO taskDTO) {
        put(taskDTO, "http://loclhost:8080/task");
    }

    public void deleteTask(String taskId) {
        delete("http://loclhost:8080/task/{taskId}", taskId);
    }

    public TaskDTO getTaskById(String taskId) {
        return get(TaskDTO.class, "http://loclhost:8080/task/{taskId}", taskId);
    }

    public TasksDTO getTask(Pageable pageable) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", String.valueOf(pageable.getPageNumber()));
        params.add("size", String.valueOf(pageable.getPageSize()));

        return getWithParam(TasksDTO.class, params, "http://loclhost:8080/task");
    }
}
