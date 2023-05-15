package com.company.springdata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, String> {

    public List<Libro> findByTitulo(String titulo);
    public List<Libro> findByAutor(String autor);
    public List<Libro> findByPrecio(double precio);
    public List<Libro> findByPrecioGreaterThan(double precio);
    public List<Libro> findByPrecioLessThan(double precio);
    public List<Libro> findByPrecioBetween(double precio1, double precio2);
    public List<Libro> findByTituloAndAutor(String titulo, String autor);
    public List<Libro> findByTituloOrAutor(String titulo, String autor);
    public List<Libro> findByTituloLike(String titulo);
    public List<Libro> findByTituloContaining(String titulo);
    public List<Libro> findByTituloStartingWith(String titulo);
    public List<Libro> findByTituloEndingWith(String titulo);
    public List<Libro> findByAutorIsNull();
    public List<Libro> findByTituloNot(String titulo);
    public List<Libro> findByTituloIn(Collection<String> collection);
    public List<Libro> findCaros();
    public List<Libro> findCarosConFecha(Date fecha);

    @Query("select avg(l.precio) from Libro l")
    public double findPrecioMedioLibros();
}
