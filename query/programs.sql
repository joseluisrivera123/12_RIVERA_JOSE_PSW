CREATE DATABASE programs;

CREATE SEQUENCE seq_programs;

CREATE TABLE programs (
    id integer default nextval('seq_programs'::regclass) not null,
    name VARCHAR(255),
    type VARCHAR(255),
    beneficiary VARCHAR(255),
    responsible VARCHAR(255),
    description TEXT,
    duration INTEGER,
    condition VARCHAR(255),
    level VARCHAR(255)
);


INSERT INTO programs (name, type, beneficiary, responsible, description, duration, condition, level)
VALUES
    ('Programa A', 'Tipo A', 'Beneficiario A', 'Responsable A', 'Descripción del programa A', 1, 'A', 'Nivel A'),
    ('Programa B', 'Tipo B', 'Beneficiario B', 'Responsable B', 'Descripción del programa B', 1, 'A', 'Nivel B'),
    ('Programa C', 'Tipo C', 'Beneficiario C', 'Responsable C', 'Descripción del programa C', 1, 'A', 'Nivel C');

select * from programs;