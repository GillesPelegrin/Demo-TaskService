package com.example.demo.acceptancetest.security;

import com.example.demo.TestClient;
import com.example.demo.gen.springbootserver.model.ProblemDto;
import com.example.demo.gen.springbootserver.model.SecurityDto;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.example.demo.FileUtil.readFile;

public class TokenVerifyTestClient extends TestClient {

    public TokenVerifyTestClient(MockMvc mockMvc) {
        super(mockMvc);
    }

    public void verifyToken(String token) {

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Authorization", List.of("Bearer " + token));

        HttpHeaders httpHeaders = HttpHeaders.readOnlyHttpHeaders(headers);

        get(null, httpHeaders, "http://localhost:8080/verify");
    }

    public ProblemDto verifyTokenWithException() {
        return get(ProblemDto.class,  "http://localhost:8080/verify");
    }
}
