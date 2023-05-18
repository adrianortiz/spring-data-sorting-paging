package com.company.springdata.controller;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "buscartodos", params = "titulo")
    public String buscarTodosPorTitulo(String titulo, Model modelo){
        Iterable<Libro> libros = repository.findByTitulo(titulo);
        modelo.addAttribute("libros", libros);
        return "libros";
    }

    @RequestMapping(value = "buscartodos", params = "autor")
    public String buscarTodosPorAutor(String autor, Model modelo) {
        Iterable<Libro> libros = repository.findByAutor(autor);
        modelo.addAttribute("libros", libros);
        return "libros";
    }

    @RequestMapping(value = "buscartodos", params = {"titulo", "autor"})
    public String buscarTodosPorTituloyAutor(String titulo, String autor, Model modelo) {
        Iterable<Libro> libros = repository.findByTituloAndAutor(titulo, autor);
        modelo.addAttribute("libros", libros);
        return "libros";
    }
}
