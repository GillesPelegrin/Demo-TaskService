package com.example.demo.acceptancetest.security;

import com.example.demo.AbstractAcceptanceTest;
import com.example.demo.Application;
import com.example.demo.gen.springbootserver.model.ProblemDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import static com.example.demo.FileUtil.readFile;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = Application.class)
public class TokenVerifyAcceptanceTest extends AbstractAcceptanceTest {

    @Test
    void tokenVerify() {
        TokenVerifyTestClient tokenVerifyTestClient = new TokenVerifyTestClient(getMockMvc());
        String token = readFile(TokenVerifyAcceptanceTest.class, "../../../../../test-jwt.txt");
        tokenVerifyTestClient.verifyToken(token);
    }

    @Test
    void tokenVerify_wrongToken() {
        TokenVerifyTestClient tokenVerifyTestClient = new TokenVerifyTestClient(getMockMvc());

        ProblemDto expected = new ProblemDto();
        expected.setType("/verify");
        expected.setTitle("UnauthorizedException");
        expected.setDetail("Authentication token is empty or no Bearer string found in the value");
        expected.setContext("");

        assertThat(tokenVerifyTestClient.verifyTokenWithException()) .isEqualTo(expected);
    }
}
