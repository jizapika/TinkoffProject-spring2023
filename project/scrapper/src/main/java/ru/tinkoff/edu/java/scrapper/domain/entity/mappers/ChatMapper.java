package ru.tinkoff.edu.java.scrapper.domain.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.ChatDao;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Chat;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Link;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ChatMapper implements RowMapper<Chat> {

    @Override
    public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Chat(
                rs.getInt("id"),
                rs.getString("last_command")
        );
    }
}
