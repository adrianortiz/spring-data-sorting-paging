package com.company.springdata;

import com.company.springdata.entity.Capitulo;
import com.company.springdata.repository.CapituloRepository;
import com.company.springdata.repository.LibroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class CapituloTest {

	@Autowired
	LibroRepository repositorioLibro;

	@Autowired
	CapituloRepository repositorioCapitulo;

	@Test
	void buscarTituloTest() {

		List<Capitulo> lista = repositorioCapitulo.findByTitulo("Bucles");
		assertThat(lista, hasItem(new Capitulo("Bucles")));
	}

	@Test
	void buscarPorLibroTituloTest() {
		List<Capitulo> lista = repositorioCapitulo.findByLibroTitulo("Java");

		assertThat(lista, hasItem(new Capitulo("Sintaxis Basica")));
		assertThat(lista, hasItem(new Capitulo("Sentencias de control")));
	}


}
