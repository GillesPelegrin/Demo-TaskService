package com.example.demo.acceptancetest;

import com.example.demo.Application;
import com.example.demo.task.TaskRepository;
import com.example.demo.testcontainer.PostgresTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ContextConfiguration(classes= Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractAcceptanceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

      PostgresTestContainer container = new PostgresTestContainer();

    // todo make a universal solution
    @BeforeEach
    void clearDatabase(@Autowired TaskRepository taskRepository) {
        taskRepository.deleteAll();
    }

    protected MockMvc getMockMvc() {
        if (this.mockMvc == null) {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }
        return mockMvc;
    }
}
