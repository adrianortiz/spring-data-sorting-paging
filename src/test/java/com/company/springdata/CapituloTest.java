package com.company.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	void buscarTodosTest() {

		List<Capitulo> lista = repositorioCapitulo.findByTitulo("Bucles");
		assertThat(lista, hasItem(new Capitulo("Bucles")));
	}


}
