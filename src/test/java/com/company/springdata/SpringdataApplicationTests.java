package com.company.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class SpringdataApplicationTests {

	@Autowired
	LibroRepository repositorioLibro;

	@Test
	void buscarTodosTest() {
		Iterable<Libro> it = repositorioLibro.findAll();
		List<Libro> miLista = new ArrayList<Libro>();
		it.forEach(System.out::println);
		it.forEach(miLista::add);

		assertThat(miLista.size(), greaterThan(6));
	}

	@Test
	void buscarUnoTest() {
		Optional<Libro> optional = repositorioLibro.findById("1A");

		if (optional.isPresent()) {
			assertThat(optional.get().getTitulo(), equalTo("Java"));
		}
	}

	@Test
	void insertarUnoTest() {

		Libro l = new Libro("1F", "Python", "Gema", new Date(), 25.00);
		repositorioLibro.save(l);

		Optional<Libro> optional = repositorioLibro.findById("1F");

		if (optional.isPresent()) {
			assertThat(optional.get().getTitulo(), equalTo("Python"));
		}
	}

	@Test
	void borrarUnoTest() {
		Libro l = new Libro("1A");
		repositorioLibro.delete(l);

		Optional<Libro> optional = repositorioLibro.findById("1A");
		assertFalse(optional.isPresent());
	}

	@Test
	void buscarVariosTest() {
		Iterable<Libro> it = repositorioLibro.findAllById(List.of("1A", "2A"));

		List<Libro> miLista = new ArrayList<Libro>();
		it.forEach(miLista::add);

		assertThat(miLista.get(0).getTitulo(), equalTo("Java"));
		assertThat(miLista.get(1).getTitulo(), equalTo("Net"));
	}



}
