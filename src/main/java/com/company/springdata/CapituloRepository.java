package com.company.springdata;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CapituloRepository extends CrudRepository<Capitulo, String> {

    List<Capitulo> findByTitulo(String titulo);
    List<Capitulo> findByLibroTitulo(String titulo);
    List<Capitulo> findByPaginas(int paginas);

}
