package com.company.springdata;

import com.company.springdata.entity.Capitulo;
import com.company.springdata.entity.Libro;
import com.company.springdata.dto.LibroCapituloTituloDTO;
import com.company.springdata.proyection.IsbnTituloProyection;
import com.company.springdata.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class LibroTest {

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

	@Test
	void buscarPorTitulo() {
		List<Libro> lista = repositorioLibro.findByTitulo("PHP");
		assertThat(lista, hasItem(new Libro("3B")));
	}

	@Test
	void buscarPorAutor() {
		List<Libro> lista = repositorioLibro.findByAutor("Cecilio");
		assertThat(lista, hasItem(new Libro("3B")));
		assertThat(lista, hasItem(new Libro("1A")));
		assertThat(lista, hasItem(new Libro("4A")));
	}

	@Test
	void buscarPorTituloYAutor() {
		List<Libro> lista = repositorioLibro.findByTituloAndAutor("Java", "Cecilio");
		assertThat(lista, hasItem(new Libro("1A")));
	}

	@Test
	void buscarPorTituloOAutor() {
		List<Libro> lista = repositorioLibro.findByTituloOrAutor("Net", "Cecilio");
		assertThat(lista, hasItem(new Libro("2A")));
		assertThat(lista, hasItem(new Libro("1A")));
		assertThat(lista, hasItem(new Libro("3B")));
		assertThat(lista, hasItem(new Libro("4A")));
	}

	@Test
	void buscarPorPrecio() {
		List<Libro> lista = repositorioLibro.findByPrecio(50);
		assertThat(lista, hasItem(new Libro("7B")));
		assertThat(lista, hasItem(new Libro("6C")));
	}

	@Test
	void buscarPorPrecioMayor() {
		List<Libro> lista = repositorioLibro.findByPrecioGreaterThan(20);
		assertThat(lista, hasItem(new Libro("7B")));
		assertThat(lista, hasItem(new Libro("6C")));
	}

	@Test
	void buscarPorPrecioMenor() {
		List<Libro> lista = repositorioLibro.findByPrecioLessThan(20);
		assertThat(lista, hasItem(new Libro("2A")));
		assertThat(lista, hasItem(new Libro("3B")));
	}

	@Test
	void buscarPorPrecioEntre() {
		List<Libro> lista = repositorioLibro.findByPrecioBetween(10, 20);
		assertThat(lista, hasItem(new Libro("2A")));
		assertThat(lista, hasItem(new Libro("5A")));
	}

	@Test
	void buscarPorTituloLike() {
		List<Libro> lista = repositorioLibro.findByTituloLike("%Java%");
		assertThat(lista, hasItem(new Libro("1A")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void buscarPorTituloContaining() {
		List<Libro> lista = repositorioLibro.findByTituloContaining("Java");
		assertThat(lista, hasItem(new Libro("1A")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void buscarPorTituloComienza() {
		List<Libro> lista = repositorioLibro.findByTituloStartingWith("Java");
		assertThat(lista, hasItem(new Libro("5A")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void buscarPorTituloFinaliza() {
		List<Libro> lista = repositorioLibro.findByTituloEndingWith("Script");
		assertThat(lista, hasItem(new Libro("5A")));
	}

	@Test
	void buscarPorTituloNot() {
		List<Libro> lista = repositorioLibro.findByTituloNot("Java");
		assertThat(lista, hasItem(new Libro("5A")));
	}

	/*
	@Test
	void buscarPorTituloIsNull() {
		List<Libro> lista = repositorioLibro.findByAutorIsNull();
		assertThat(lista, hasItem(new Libro("7B")));
	}
	 */

	@Test
	void buscarPorTituloEnConjunto() {
		List<String> listaTitulo = new ArrayList<String>();
		listaTitulo.add("Java");
		listaTitulo.add("PHP");

		List<Libro> lista = repositorioLibro.findByTituloIn(listaTitulo);
		assertThat(lista, hasItem(new Libro("1A")));
		assertThat(lista, hasItem(new Libro("3B")));
	}

	@Test
	void buscarPorCaros() {
		List<Libro> lista = repositorioLibro.findCaros();
		assertThat(lista, hasItem(new Libro("6C")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void buscarPorCarosConFecha() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-YYYY");
		Date fecha = null;

		try {
			fecha = f.parse("01-01-2021");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Libro> lista = repositorioLibro.findCarosConFecha(fecha);
		assertThat(lista, hasItem(new Libro("6C")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void calcularMediaPrecioLibro() {
		double media = repositorioLibro.findPrecioMedioLibros();
		assertThat(media, greaterThan(10.0));
	}

	@Test
	void buscarTodosNativos() {
		List<Libro> lista = repositorioLibro.findAllNativo();
		assertThat(lista, hasItem(new Libro("6C")));
		assertThat(lista, hasItem(new Libro("7B")));
	}

	@Test
	void buscarTodosConCapitulos() {
		List<Libro> lista = repositorioLibro.findAllWithCapitulos();
		int i = lista.indexOf(new Libro("1A"));
		Libro libro = lista.get(i);
		assertThat(libro.getCapitulos(),  hasItem(new Capitulo("Bucles")));
	}

	@Test
	void buscarTodosDTO() {
		List<LibroCapituloTituloDTO> lista = repositorioLibro.findLibroCapituloDTO();
		assertNotNull(lista);

		LibroCapituloTituloDTO miDTO = new LibroCapituloTituloDTO("Java", "Sintaxis Basica");
		assertThat(lista, hasItem(miDTO));
	}

	@Test
	void buscarTodosPoyection() {
		List<IsbnTituloProyection> lista = repositorioLibro.findAllByOrderByAutor();
		assertNotNull(lista);
		assertEquals(lista.get(0).getIsbn(), "9A");
	}

}
