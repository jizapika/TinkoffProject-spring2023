package ru.tinkoff.edu.java.scrapper.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;

@SpringBootTest
class LinkDaoTest extends IntegrationEnvironment {
    @Autowired
    private LinkDao linkRepository;
    @Autowired
    private ChatDao chatRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Rollback
    @Test
    void findAll() {
    }

    @Transactional
    @Rollback
    @Test
    void findById() {
    }

    @Transactional
    @Rollback
    @Test
    void update() {
    }

    @Transactional
    @Rollback
    @Test
    void save() {
    }

    @Transactional
    @Rollback
    @Test
    void delete() {
    }
}