package com.company.springdata.repository;

import com.company.springdata.entity.Capitulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "capitulos")
public interface CapituloRepository extends CrudRepository<Capitulo, String> {

    List<Capitulo> findByTitulo(String titulo);
    List<Capitulo> findByLibroTitulo(String titulo);
    List<Capitulo> findByPaginas(int paginas);

}
