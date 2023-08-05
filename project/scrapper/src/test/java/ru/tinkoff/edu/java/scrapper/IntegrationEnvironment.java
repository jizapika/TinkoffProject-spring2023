package ru.tinkoff.edu.java.scrapper;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class IntegrationEnvironment {
    @Container
    static final PostgreSQLContainer<?> POSTGRES_CONTAINER;

    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer("postgres")
                .withDatabaseName("postgresql");
        POSTGRES_CONTAINER.start();
    }

    public static String getJdbcUrl() {
        return POSTGRES_CONTAINER.getJdbcUrl();
    }

    public static String getUsername() {
        return POSTGRES_CONTAINER.getUsername();
    }

    public static String getPassword() {
        return POSTGRES_CONTAINER.getPassword();
    }
}