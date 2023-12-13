package com.example.demo;

import com.example.demo.testcontainer.PostgresTestContainer;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class AbstractAcceptanceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    PostgresTestContainer container = new PostgresTestContainer();

    // todo make a universal solution
    @BeforeEach
    void clearDatabase(@Autowired JdbcTemplate jdbcTemplate) {
        truncateTable(jdbcTemplate, "TASK");
    }

    //    @Transactional
    public void truncateTable(JdbcTemplate jdbcTemplate, String tableName) {
        String sql = "TRUNCATE TABLE " + tableName;
        jdbcTemplate.execute(sql);
    }

    protected MockMvc getMockMvc() {
        if (this.mockMvc == null) {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        }
        return mockMvc;
    }
}
