package com.example.demo.acceptancetest.exception;

import com.example.demo.AbstractAcceptanceTest;
import com.example.demo.Application;
import com.example.demo.gen.springbootserver.model.ProblemDto;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.demo.TestClient.toObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = Application.class)
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

    @Test
    void assertRunTimeException() throws Exception {
        MvcResult mvcResult = getPerform("runTimeException")
                .andExpect(status().isInternalServerError())
                .andReturn();

        ProblemDto expected = new ProblemDto();
        expected.setType("/runTimeException");
        expected.setTitle("RuntimeException");
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
