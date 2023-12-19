CREATE DATABASE db_teen;

CREATE SEQUENCE seq_teen;
CREATE TABLE teen(
     id_adolescente integer default nextval('seq_teen' :: regclass) PRIMARY KEY not null,
     name varchar(200),
     surnamefather varchar(200),
     surnamemother varchar(200),
     dni varchar(8),
     estado char (1) NOT NULL DEFAULT ('A')
);
INSERT INTO teen (name, surnamefather, surnamemother,dni,estado) VALUES
    ('Jose', 'Rivera', 'Yarihuaman',75107263,'A');

INSERT INTO teen (name, surnamefather, surnamemother,dni,estado) VALUES
    ('Jesus', 'Sulca', 'Ciprian',75107265,'A');