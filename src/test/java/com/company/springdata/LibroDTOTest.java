package com.company.springdata;

import com.company.springdata.dto.LibroCapituloTituloDTO;
import com.company.springdata.entity.Capitulo;
import com.company.springdata.entity.Libro;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql({"/squema.sql", "/data.sql"})
class LibroDTOTest {

	@Autowired
	LibroRepository repositorioLibro;

	@Test
	void buscarTodosDTO() {
		List<LibroCapituloTituloDTO> lista = repositorioLibro.findLibroCapituloDTO();
		assertNotNull(lista);

		LibroCapituloTituloDTO miDTO = new LibroCapituloTituloDTO("Java", "Sintaxis Basica");
		assertThat(lista, hasItem(miDTO));
	}

}
