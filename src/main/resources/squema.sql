DROP TABLE IF EXISTS capitulos;
CREATE TABLE capitulos (
    titulo varchar(25),
    paginas integer,
    libros_isbn varchar(10)
);

DROP TABLE IF EXISTS libros;
CREATE TABLE libros (
    isbn varchar(10),
    titulo varchar(25),
    autor varchar(25),
    fecha date,
    precio double,
    PRIMARY KEY (isbn)
);