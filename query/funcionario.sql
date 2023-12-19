-- Crear la tabla "funcionary"

Create database db_funcionary;

CREATE SEQUENCE seq_funcionary;
CREATE TABLE funcionary (
    id_funcionary integer default nextval('seq_funcionary'::regclass) not null,
    name VARCHAR(255),
    surnamefather VARCHAR(255),
    surnamemother VARCHAR(255),
    dni VARCHAR(255),
    phonenumber VARCHAR(255),
    range VARCHAR(255),
    confirmation VARCHAR(255),
    department VARCHAR(255),
    address VARCHAR(255),
    email VARCHAR(255),
    status VARCHAR(255)
);

-- Insertar datos de ejemplo
INSERT INTO funcionary (name, surnamefather, surnamemother, dni, phonenumber, range, confirmation, department, address, email, status)
VALUES ('Robert', 'Sanchez', 'Rodriges', '12345678', '123-456-789', 'Gerente', 'Confirmado', 'Departamento Ejemplo', '123 Calle Ejemplo', 'ejemplo@email.com', 'A');
