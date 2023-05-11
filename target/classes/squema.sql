DROP TABLE IF EXISTS libros;

CREATE TABLE libros (
    isbn varchar(10),
    titulo varchar(25),
    autor varchar(25),
    fecha date,
    precio double,
    PRIMARY KEY (isbn)
);