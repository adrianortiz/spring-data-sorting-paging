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

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class OrdenacionesTest {

	@Autowired
	LibroRepository repositorioLibro;

	@Test
	void buscarTodosOrdenadorPorTitulo() {
		List<Libro> lista = repositorioLibro.findByOrderByTitulo();
		assertNotNull(lista);

		Iterable<Libro> it = repositorioLibro.findAll();
		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		List<Libro> listaOrdenada = lista2.stream()
				.sorted(Comparator.comparing(Libro::getTitulo))
				.collect(Collectors.toList());
		assertEquals(lista, listaOrdenada);
	}

	@Test
	void buscarTodosOrdenadorPorTituloyAutor() {
		List<Libro> lista = repositorioLibro.findByOrderByTituloAscAutorAsc();
		assertNotNull(lista);

		Iterable<Libro> it = repositorioLibro.findAll();
		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		List<Libro> listaOrdenada = lista2.stream()
				.sorted(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor))
				.collect(Collectors.toList());
		assertEquals(lista, listaOrdenada);
	}

	@Test
	void buscarTodosOrdenadosPorTituloParametro() {
		Iterable<Libro> it = repositorioLibro.findAll(Sort.by("titulo").ascending());

		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		List<Libro> listaOrdenada = lista2.stream()
				.sorted(Comparator.comparing(Libro::getTitulo))
				.collect(Collectors.toList());

		assertEquals(lista2, listaOrdenada);
	}

	@Test
	void buscarTodosOrdenadosPorTituloyAutorParametro() {
		Iterable<Libro> it = repositorioLibro.findAll(Sort.by("titulo").ascending().and(Sort.by("autor").ascending()));

		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		List<Libro> listaOrdenada = lista2.stream()
				.sorted(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor))
				.collect(Collectors.toList());

		assertEquals(lista2, listaOrdenada);
	}

	@Test
	void buscarTodosOrdenadosPorTituloyAutorParametroTyped() {
		Sort.TypedSort<Libro> ordenar = Sort.sort(Libro.class);
		Sort ordenFinal = ordenar.by(Libro::getTitulo).ascending().and(ordenar.by(Libro::getAutor).ascending());

		Iterable<Libro> it = repositorioLibro.findAll(ordenFinal);
		List<Libro> lista2 = new ArrayList<Libro>();
		it.forEach(lista2::add);

		List<Libro> listaOrdenada = lista2.stream()
				.sorted(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor))
				.collect(Collectors.toList());

		assertEquals(lista2, listaOrdenada);
	}

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
