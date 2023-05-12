package com.company.springdata;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, String> {

    public List<Libro> findByTitulo(String titulo);
    public List<Libro> findByAutor(String autor);

}
