--liquibase formatted sql
--changeset GillesPelegrin:202306292112_create_task_table

CREATE TABLE TASK
(
    id   VARCHAR(36) PRIMARY KEY,
    title VARCHAR(255) not null,
    message VARCHAR(255) not null,
    creationDate DATE not null,
    updateDate DATE not null
);