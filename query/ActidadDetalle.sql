CREATE DATABASE db_ActividadDetalle;

CREATE SEQUENCE seq_actividadDetalle;

CREATE TABLE activities_teenager (
    id_act_teen integer default nextval('seq_actividadDetalle'::regclass) not null,
    id_activities integer,
    id_teenager INTEGER,
	start_date date,
	duration VARCHAR(255),
	notes VARCHAR(255),
	participation_status VARCHAR(255),
	active VARCHAR(1)
)


-- Inserción 1
INSERT INTO activities_teenager (id_activities, id_teenager, start_date, duration, notes, participation_status, active)
VALUES (1, 1, '2023-01-01', '2 hours', 'Note 1', 'Present', 'Y');

-- Inserción 2
INSERT INTO activities_teenager (id_activities, id_teenager, start_date, duration, notes, participation_status, active)
VALUES (2, 2, '2023-02-01', '1 hour', 'Note 2', 'Absent', 'N');