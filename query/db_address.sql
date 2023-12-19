CREATE SEQUENCE seq_address;

CREATE TABLE address (
                         id INTEGER default nextval('seq_address'::regclass) not null,
                         address VARCHAR(255),
                         idadolescent INTEGER,
                         active CHAR(1),
                         ubigeo CHAR(6)
)

    INSERT INTO address (address, idadolescent, active, ubigeo)
VALUES ('av brazil', 1, 'A', '123456');