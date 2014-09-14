--liquibase formatted sql

--changeset tstout:create schema
create schema if not exists DB_IO;
--rollback drop schema db_io;

create table db_io.logs (
  id           int identity(1,1) primary key not null
  ,when        datetime not null
  ,level       varchar(100) not null
  ,msg         varchar(1024) not null
  ,logger_name varchar(100) not null
  ,thread      varchar(100) not null
);
--rollback drop table db_io.logs;
