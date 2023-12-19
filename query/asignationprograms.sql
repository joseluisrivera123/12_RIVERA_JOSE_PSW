CREATE DATABASE db_asignationprograms;

CREATE SEQUENCE seq_asignationprograms;

CREATE TABLE asignationprograms (
	id integer default nextval('seq_asignationprograms'::regclass) not null,
    id_programs INTEGER,
    id_activities INTEGER,
    date_asignation DATE,
    direction VARCHAR(255),
    name_programs VARCHAR(255),
    name_activities VARCHAR(255),
    active VARCHAR(255)
);

INSERT INTO asignationprograms (id_programs, id_activities, date_asignation, direction, name_programs, name_activities, active)
VALUES
    (1, 1, '2023-12-07', 'Ejemplo dirección', 'Barrio seguro', 'evento 1', 'Sí'),
    (2, 2, '2023-12-07', 'Otra dirección', '', 'evento 2', 'No'),
    (3, 3, '2023-12-07', 'Dirección de prueba', 'evento 3', 'Actividad 3', 'Sí');
	
select * from asignationprograms;