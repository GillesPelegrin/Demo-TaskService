package com.example.demo.acceptancetest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class TestClient {

    private MockMvc mockMvc;

    public TestClient(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public <T> T get(Class<T> responseBody, String urlTemplate, Object... uriVariables) {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate, uriVariables)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            return toObject(mvcResult.getResponse().getContentAsString(), responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getWithParam(Class<T> responseBody, MultiValueMap<String, String> params, String urlTemplate, Object... uriVariables) {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate, uriVariables)
                            .queryParams(params)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            return toObject(mvcResult.getResponse().getContentAsString(), responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void post(Object o, String urlTemplate, Object... uriVariables) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate, uriVariables)
                            .content(asJsonString(o))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void put(Object o, String urlTemplate, Object... uriVariables) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.put(urlTemplate, uriVariables)
                            .content(asJsonString(o))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String urlTemplate, Object... uriVariables) {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate, uriVariables)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
