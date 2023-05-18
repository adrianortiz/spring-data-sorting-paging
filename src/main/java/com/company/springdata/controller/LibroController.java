package com.company.springdata.controller;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    LibroRepository repository;

    @RequestMapping(value = "buscartodos", params = "orden")
    public String buscarTodos(Model modelo, String orden) {

        Iterable<Libro> libros = null;
        libros = repository.findAll(Sort.by(orden));

        /*
        if (orden.equals("isbn")) {
            libros = repository.findAllByOrderByIsbn();
        } else if (orden.equals("titulo")) {
            libros = repository.findAllByOrderByTitulo();
        } else if (orden.equals("autor")) {
            libros = repository.findAllByOrderByAutor();
        } else if (orden.equals("precio")) {
            libros = repository.findAllByOrderByPrecio();
        } else if (orden.equals("fecha")) {
            libros = repository.findAllByOrderByFecha();
        } else {
            libros = repository.findAll();
        }
        */

        modelo.addAttribute("libros", libros);
        return "libros";
    }

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
