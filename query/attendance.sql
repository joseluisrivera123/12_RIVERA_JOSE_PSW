Create database db_attendance;

CREATE SEQUENCE seq_attendance;

CREATE TABLE attendance (
    id integer default nextval('seq_attendance'::regclass) not null,
    idactiviti INTEGER,
    idadolescente INTEGER,
    asistencia CHAR(1),
	active CHAR(1),
	date date
);

INSERT INTO attendance (idActiviti, idAdolescente, asistencia,active,date) VALUES
(1, 1, 'A','A','24/11/2023');

select * from attendance;