package com.example.demo.acceptancetest.client;

import com.example.demo.gen.springbootserver.model.SecurityDto;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Base64;
import java.util.List;

public class SecurityTestClient extends TestClient {

    public SecurityTestClient(MockMvc mockMvc) {
        super(mockMvc);
    }

    public SecurityDto getAccessToken(String username, String password) {
        String encodedString = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Authorization", List.of("Basic " + encodedString));

        HttpHeaders httpHeaders = HttpHeaders.readOnlyHttpHeaders(headers);

        return get(SecurityDto.class, httpHeaders, "http://localhost:8080/api/v1/security");
    }
}
