package ru.tinkoff.edu.java.scrapper.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.scrapper.domain.entity.dto.Link;
import ru.tinkoff.edu.java.scrapper.domain.entity.mappers.LinkMapper;

import java.util.List;
import java.util.Optional;

@Component
public class LinkDao implements Dao<Long, Link> {

    private JdbcTemplate jdbcTemplate;
    private LinkMapper linkMapper;

    public LinkDao(JdbcTemplate jdbcTemplate, LinkMapper linkMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.linkMapper = linkMapper;
    }

    @Override
    public List<Link> findAll() {
        return jdbcTemplate.query("select id, chat_id, uri, last_modified from link", linkMapper);
    }

    @Override
    public Optional<Link> findById(Long id) {
        return jdbcTemplate.query("select id, chat_id, uri, last_modified from link where id = ?",
                        linkMapper, id)
                .stream()
                .findAny();
    }

    @Override
    public void update(Link updatedLink) {
        jdbcTemplate.update(
                "update link set chat_id = ?, uri = ?, last_modified = ? where id = ?",
                updatedLink.chat().id(), updatedLink.uri(),
                updatedLink.lastModified(), updatedLink.id());
    }

    @Override
    public Link save(Link link) {
        return new Link(
                jdbcTemplate.query(
                        "insert into link(chat_id, uri, last_modified) values (?, ?, ?) returning id",
                        (rs, rowNum) -> rs.getLong("id"),
                        link.chat().id(), link.uri(), link.lastModified()
                ).get(0),
                link.chat(),
                link.uri(),
                link.lastModified()
        );
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.query("delete from link where id = ? returning id",
                linkMapper, id).size() > 0;
    }
}