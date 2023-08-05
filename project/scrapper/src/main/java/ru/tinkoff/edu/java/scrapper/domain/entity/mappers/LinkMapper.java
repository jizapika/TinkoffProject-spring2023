package ru.tinkoff.edu.java.scrapper.domain.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.ChatDao;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Link;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LinkMapper implements RowMapper<Link> {
    private ChatDao chatDao;

    public LinkMapper(ChatDao chatDao) {
        this.chatDao = chatDao;
    }

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Link(
                rs.getLong("id"),
                chatDao.findById(rs.getInt("chat_id")).orElse(null),
                rs.getString("uri"),
                rs.getTimestamp("last_modified").toLocalDateTime()
        );
    }
}
