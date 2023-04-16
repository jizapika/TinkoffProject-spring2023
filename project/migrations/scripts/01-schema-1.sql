create schema bot_storage;

create table bot_storage.chats (
    id int primary key,
    last_command varchar(50)
);

create table bot_storage.links (
    id int primary key,
    chat_id int references chats(id),
    uri varchar(255),
    last_modified timestamp
);