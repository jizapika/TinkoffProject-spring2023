package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Chat;
import ru.tinkoff.edu.java.scrapper.domain.entity.mappers.ChatMapper;

import java.util.List;
import java.util.Optional;

@Component
public class ChatDao implements Dao<Integer, Chat> {
    private JdbcTemplate jdbcTemplate;

    public ChatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Chat> findAll() {
        return jdbcTemplate.query("select id, last_command from chat", new ChatMapper());
    }

    @Override
    public Optional<Chat> findById(Integer id) {
        return jdbcTemplate.query("select id, last_command from chat where id = ?",
                new ChatMapper(), id).stream().findAny();
    }

    @Override
    public void update(Chat updatedChat) {
        jdbcTemplate.update("update chat set last_command = ? where id = ?",
                updatedChat.lastCommand(), updatedChat.id());
    }

    @Override
    public Chat save(Chat chat) {
        jdbcTemplate.update("insert into chat(id, last_command) values (?, ?)",
                chat.id(), chat.lastCommand());
        return chat;
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.query("delete from chat where id = ? returning id",
                new ChatMapper(), id).size() > 0;
    }
}
