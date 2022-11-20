-- liquibase formatted sql

-- changeset denis:1
CREATE TABLE task (
id serial,
chatId bigint,
messageText text,
dataTime timestamp
);