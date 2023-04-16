--liquibase formatted sql
--changeset jizapika:1
create table chats (
    id int primary key,
    last_command varchar(50)
);