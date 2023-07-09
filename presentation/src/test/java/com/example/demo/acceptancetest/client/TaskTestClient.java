package com.example.demo.acceptancetest.client;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskTestClient {

    private MockMvc mockMvc;

    public TaskTestClient(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public void createTask(CreateTaskDTO taskDTO) throws Exception {

        mockMvc.perform(post("http://loclhost:8080/task")
                        .content(asJsonString(taskDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    public void updateTask(UpdateTaskDTO taskDTO) throws Exception {
        mockMvc.perform(put("http://loclhost:8080/task")
                        .content(asJsonString(taskDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public void deleteTask(Long taskId) throws Exception {
        mockMvc.perform(delete("http://loclhost:8080/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public void getTaskById(Long taskId) throws Exception {
        mockMvc.perform(get("http://loclhost:8080/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
