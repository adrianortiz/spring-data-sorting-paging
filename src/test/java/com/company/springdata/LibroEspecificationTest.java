package com.company.springdata;

import com.company.springdata.dto.LibroCapituloTituloDTO;
import com.company.springdata.entity.Capitulo;
import com.company.springdata.entity.Libro;
import com.company.springdata.proyection.IsbnTituloProyection;
import com.company.springdata.repository.LibroRepository;
import com.company.springdata.specification.LibroEspecificacion;
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
class LibroEspecificationTest {

	@Autowired
	LibroRepository repositorioLibro;

	@Test
	void buscarTodosEspecificationTest() {
		List<Libro> lista = repositorioLibro.findAll(LibroEspecificacion.tituloStartsWith("Java"));
		assertNotNull(lista);
		assertThat(lista, hasItem(new Libro("6C")));
	}
}
