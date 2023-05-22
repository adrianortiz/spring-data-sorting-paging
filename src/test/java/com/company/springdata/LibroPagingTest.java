package com.company.springdata;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class LibroPagingTest {

	@Autowired
	LibroRepository repositorioLibro;

	@Test
	void buscarTodosPaginado() {
		Page<Libro> pagina = repositorioLibro.findAll(PageRequest.of(0, 2));
		List<Libro> libros = pagina.getContent();

		Iterable<Libro> it = repositorioLibro.findAll();
		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		assertEquals(libros, lista2.stream().limit(2).collect(Collectors.toList()));
	}

	@Test
	void buscarTodosPaginadoSiguiente() {
		Page<Libro> pagina = repositorioLibro.findAll(PageRequest.of(0, 2));
		List<Libro> libros = pagina.getContent();

		Iterable<Libro> it = repositorioLibro.findAll();
		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		Page<Libro> siguiente = repositorioLibro.findAll(pagina.nextPageable());

		assertEquals(siguiente.getContent(), lista2.stream()
				.skip(2)
				.limit(2)
				.collect(Collectors.toList()));
	}


}
