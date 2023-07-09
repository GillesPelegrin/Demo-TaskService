package com.example.demo.acceptancetest.client;

import com.example.demo.dto.task.CreateTaskDTO;
import com.example.demo.dto.task.TaskDTO;
import com.example.demo.dto.task.TasksDTO;
import com.example.demo.dto.task.UpdateTaskDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    public void deleteTask(String taskId) throws Exception {
        mockMvc.perform(delete("http://loclhost:8080/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public TaskDTO getTaskById(String taskId) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://loclhost:8080/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return toObject(mvcResult.getResponse().getContentAsString(), TaskDTO.class);
    }

    public TasksDTO getTask(Pageable pageable) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://loclhost:8080/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return toObject(mvcResult.getResponse().getContentAsString(), TasksDTO.class);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> name) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            return mapper.readValue(json, name);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
