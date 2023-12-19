CREATE DATABASE db_Activities

CREATE SEQUENCE seq_activities;

CREATE TABLE activities (
    id integer default nextval('seq_activities'::regclass) not null,
    name VARCHAR(255),
    description TEXT,
    date DATE,
    duration VARCHAR(50),
    location VARCHAR(255),
    active char(1),
    type_pronacej VARCHAR(50),
    type_soa VARCHAR(50)
);


-- Inserción 1
INSERT INTO activities (name, description, date, duration, location, active, type_pronacej, type_soa)
VALUES ('Evento 1', 'Descripción del evento 1', '2023-10-18', '2 horas', 'Ubicación 1', 'A', 'Pronacej 1', 'SOA 1');

-- Inserción 2
INSERT INTO activities (name, description, date, duration, location, active, type_pronacej, type_soa)
VALUES ('Evento 2', 'Descripción del evento 2', '2023-10-19', '3 horas', 'Ubicación 2', 'A', 'Pronacej 2', 'SOA 2');


Select * from activities;