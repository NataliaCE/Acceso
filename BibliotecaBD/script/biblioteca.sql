DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE libros (
	id INTEGER PRIMARY KEY,
    titulo VARCHAR(50),
    autor VARCHAR(30),
    prestado BOOLEAN
    );

INSERT INTO libros VALUES (1, "El nombre del viento", "Patrick Rothfuss", true),
						  (2, "El señor de los anillos", "J.R.R. Tolkien", true),
                          (3, "Alicia en el pais de las maravillas", "Lewis Carrol", false),
                          (4, "Orgullo y prejuicio", "Jane Austen", true), 
                          (5, "Seis de cuervos", "Leigh Bardugo", false), 
                          (6, "Buenos presagios", "Neil Gaiman", false);