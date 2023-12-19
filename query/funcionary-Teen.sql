CREATE DATABASE db_Funcionary_Teen

CREATE SEQUENCE seq_funcionarios_adolescente;

CREATE TABLE IF NOT EXISTS funcionarios_adolescente (
    id_funcionaryteend integer default nextval('seq_funcionarios_adolescente'::regclass) not null,
    description VARCHAR(255),
    estado VARCHAR(255),
    id_adolescente INTEGER,
    id_funcionary INTEGER,
    start_date DATE,
    register_date DATE
);


INSERT INTO funcionarios_adolescente (description, estado, id_adolescente, id_funcionary , start_date , register_date)
VALUES ('Descripción 2', 'A', 2, 2 , '2023-12-1' ,'2023-12-1');