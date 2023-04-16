--liquibase formatted sql
--changeset jizapika:2
create table links (
    id int primary key,
    chat_id int references chats(id),
    uri varchar(255),
    last_modified timestamp
);