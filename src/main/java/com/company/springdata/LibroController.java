package com.company.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    LibroRepository repository;

    @RequestMapping("buscartodos")
    public String buscarTodos(Model model) {

        Iterable<Libro> libros = repository.findAll();
        model.addAttribute("libros", libros);

        return "libros";
    }
}
