package com.company.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

}
