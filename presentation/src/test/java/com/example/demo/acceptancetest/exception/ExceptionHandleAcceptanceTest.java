package com.example.demo.acceptancetest.exception;

import com.example.demo.acceptancetest.AbstractAcceptanceTest;
import com.example.demo.acceptancetest.security.SecurityTestClient;
import com.example.demo.gen.springbootserver.model.ProblemDto;
import com.example.demo.util.DateTimeWrapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import static com.example.demo.acceptancetest.TestClient.toObject;
import static com.example.demo.testconstant.SecurityDtoTestConstant.getSecurityDTO;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExceptionHandleAcceptanceTest extends AbstractAcceptanceTest {

    @Test
    void assertNotFoundException() throws Exception {
        MvcResult mvcResult = getPerform("notfoundException")
                .andExpect(status().isNotFound())
                .andReturn();

        ProblemDto expected = new ProblemDto();
        expected.setType("/notfoundException");
        expected.setTitle("NotFoundException");
        expected.setDetail("message");
        expected.setContext("");

        assertThat(toObject(mvcResult.getResponse().getContentAsString(), ProblemDto.class))
                .isEqualTo(expected);
    }

    @Test
    void assertUnauthorizedException() throws Exception {
        MvcResult mvcResult = getPerform("unauthorizedException")
                .andExpect(status().isUnauthorized())
                .andReturn();

        ProblemDto expected = new ProblemDto();
        expected.setType("/unauthorizedException");
        expected.setTitle("UnauthorizedException");
        expected.setDetail("message");
        expected.setContext("");

        assertThat(toObject(mvcResult.getResponse().getContentAsString(), ProblemDto.class))
                .isEqualTo(expected);
    }

    @Test
    void assertInternalErrorException() throws Exception {
        MvcResult mvcResult = getPerform("internalErrorException")
                .andExpect(status().isInternalServerError())
                .andReturn();

        ProblemDto expected = new ProblemDto();
        expected.setType("/internalErrorException");
        expected.setTitle("InternalErrorException");
        expected.setDetail("message");
        expected.setContext("");

        assertThat(toObject(mvcResult.getResponse().getContentAsString(), ProblemDto.class))
                .isEqualTo(expected);
    }

    @NotNull
    private ResultActions getPerform(String error) throws Exception {
        return getMockMvc().perform(MockMvcRequestBuilders.get("http://localhost:8080/" + error)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .accept(MediaType.APPLICATION_PROBLEM_JSON));
    }
}
