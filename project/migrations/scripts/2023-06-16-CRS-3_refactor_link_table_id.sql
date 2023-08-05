--liquibase formatted sql
--changeset jizapika:5

create sequence link_id_seq;

-- Изменение типа столбца на bigint
alter table link
alter column id type bigint;

-- Связывание последовательности с столбцом
alter table link
alter column id set default nextval('link_id_seq');

-- Изменение владельца последовательности на столбец
alter sequence link_id_seq owned by link.id;