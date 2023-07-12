package com.example.demo.acceptancetest;

import com.example.demo.TaskRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractAcceptanceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    public static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer()
            .withDatabaseName("db")
            .withUsername("test_user")
            .withPassword("test_password");

    @BeforeAll
    static void setUp() {
        postgresqlContainer.setWaitStrategy(
                Wait.defaultWaitStrategy()
                        .withStartupTimeout(Duration.of(60, SECONDS)));
        postgresqlContainer.start();
    }

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgresqlContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgresqlContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", postgresqlContainer::getDriverClassName);
    }


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
