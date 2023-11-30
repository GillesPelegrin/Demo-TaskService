package com.example.demo.acceptancetest.client;

import com.example.demo.gen.springbootserver.model.CreateTaskDto;
import com.example.demo.gen.springbootserver.model.GetTasks200ResponseDto;
import com.example.demo.gen.springbootserver.model.TaskDto;
import com.example.demo.gen.springbootserver.model.UpdateTaskDto;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TaskTestClient extends TestClient {

    public TaskTestClient(MockMvc mockMvc) {
        super(mockMvc);
    }

    public void createTask(CreateTaskDto taskDTO) {
        post(taskDTO, "http://localhost:8080/api/v1/task");
    }

    public void updateTask(UpdateTaskDto taskDTO) {
        put(taskDTO, "http://localhost:8080/api/v1/task");
    }

    public void deleteTask(String taskId) {
        delete("http://localhost:8080/api/v1/task/{taskId}", taskId);
    }

    public TaskDto getTaskById(String taskId) {
        return get(TaskDto.class, "http://localhost:8080/api/v1/task/{taskId}", taskId);
    }

    public GetTasks200ResponseDto getTasks(Pageable pageable) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", String.valueOf(pageable.getPageNumber()));
        params.add("size", String.valueOf(pageable.getPageSize()));

        return getWithParam(GetTasks200ResponseDto.class, params, "http://localhost:8080/api/v1/task");
    }
}
