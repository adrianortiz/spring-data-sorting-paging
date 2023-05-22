package com.company.springdata.repository;

import com.company.springdata.dto.LibroCapituloTituloDTO;
import com.company.springdata.entity.Libro;
import com.company.springdata.proyection.IsbnTituloProyection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface LibroRepository extends PagingAndSortingRepository<Libro, String>, QueryByExampleExecutor<Libro>, JpaSpecificationExecutor<Libro> {

    public List<Libro> findByTitulo(String titulo);
    public List<Libro> findByTitulo(String titulo, Sort orden);
    public List<Libro> findByTitulo(String titulo, Pageable paginable);

    public List<Libro> findByAutor(String autor);
    public List<Libro> findByAutor(String autor, Sort orden);
    public List<Libro> findByAutor(String autor, Pageable paginable);

    public List<Libro> findByPrecio(double precio);
    public List<Libro> findByPrecioGreaterThan(double precio);
    public List<Libro> findByPrecioLessThan(double precio);
    public List<Libro> findByPrecioBetween(double precio1, double precio2);

    public List<Libro> findByTituloAndAutor(String titulo, String autor);
    public List<Libro> findByTituloAndAutor(String titulo, String autor, Sort orden);
    public List<Libro> findByTituloAndAutor(String titulo, String autor, Pageable paginable);

    public List<Libro> findByTituloOrAutor(String titulo, String autor);
    public List<Libro> findByTituloOrAutor(String titulo, String autor, Sort orden);
    public List<Libro> findByTituloOrAutor(String titulo, String autor, Pageable paginable);

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

    @Query(value = "select * from Libros", nativeQuery = true)
    public List<Libro> findAllNativo();

    public List<Libro> findAllWithCapitulos();

    @Query("select distinct new com.company.springdata.dto.LibroCapituloTituloDTO(l.titulo, c.titulo) from Libro l, Capitulo c")
    public List<LibroCapituloTituloDTO> findLibroCapituloDTO();

    public List<Libro> findByOrderByTitulo();
    public List<Libro> findByOrderByTituloAscAutorAsc();

    public List<Libro> findAllByOrderByIsbn();
    public List<Libro> findAllByOrderByTitulo();
    public List<Libro> findAllByOrderByPrecio();
    public List<Libro> findAllByOrderByFecha();

    public List<IsbnTituloProyection> findAllByOrderByAutor();

}
