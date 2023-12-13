package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class TestClient {

    private MockMvc mockMvc;
    private String authToken;

    public TestClient(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public <T> T get(Class<T> responseBody, String urlTemplate, Object... uriVariables) {
        return get(responseBody, emptyHttpHeader(), urlTemplate, uriVariables);
    }


    public <T> T get(Class<T> responseBody, HttpHeaders httpHeaders, String urlTemplate) {
        return get(responseBody, httpHeaders, urlTemplate, new Object());
    }

    public <T> T get(Class<T> responseBody, HttpHeaders httpHeaders, String urlTemplate, Object... uriVariables) {
        return get(responseBody, emptyParams(), httpHeaders, urlTemplate, uriVariables);
    }

    public <T> T get(Class<T> responseBody, MultiValueMap<String, String> params, String urlTemplate, Object... uriVariables) {
        return get(responseBody, params, emptyHttpHeader(), urlTemplate, uriVariables);
    }

    public <T> T get(Class<T> responseBody, MultiValueMap<String, String> params, HttpHeaders httpHeaders, String urlTemplate, Object... uriVariables) {
        try {
            addAuthTokenToHttpHeader(authToken, httpHeaders);

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate, uriVariables)
                            .headers(httpHeaders)
                            .queryParams(params)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            if (responseBody != null) {
                return toObject(mvcResult.getResponse().getContentAsString(), responseBody);
            }
            return (T) new Object();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void post(Object o, String urlTemplate, Object... uriVariables) {
        try {
            HttpHeaders httpHeaders = emptyHttpHeader();
            addAuthTokenToHttpHeader(authToken, httpHeaders);

            mockMvc.perform(MockMvcRequestBuilders.post(urlTemplate, uriVariables)
                            .content(asJsonString(o))
                            .headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void put(Object o, String urlTemplate, Object... uriVariables) {
        try {
            HttpHeaders httpHeaders = emptyHttpHeader();
            addAuthTokenToHttpHeader(authToken, httpHeaders);

            mockMvc.perform(MockMvcRequestBuilders.put(urlTemplate, uriVariables)
                            .content(asJsonString(o))
                            .headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String urlTemplate, Object... uriVariables) {
        try {
            HttpHeaders httpHeaders = emptyHttpHeader();
            addAuthTokenToHttpHeader(authToken, httpHeaders);

            mockMvc.perform(MockMvcRequestBuilders.delete(urlTemplate, uriVariables)
                            .headers(httpHeaders)
                            .contentType(MediaType.APPLICATION_JSON))
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

    private static HttpHeaders addAuthTokenToHttpHeader(String authToken, HttpHeaders httpHeaders) {
        if (authToken != null) {
            httpHeaders.setBearerAuth(authToken);
        }
        return httpHeaders;
    }

    private static HttpHeaders emptyHttpHeader() {
        return HttpHeaders.writableHttpHeaders(HttpHeaders.EMPTY);
    }

    private static MultiValueMap<String, String> emptyParams() {
        return new LinkedMultiValueMap<>();
    }

    protected void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
