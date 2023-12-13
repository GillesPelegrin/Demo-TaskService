package com.example.demo;

import com.example.demo.testcontainer.PostgresTestContainer;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public abstract class AbstractIntegrationTest {

    PostgresTestContainer container = new PostgresTestContainer();
}
