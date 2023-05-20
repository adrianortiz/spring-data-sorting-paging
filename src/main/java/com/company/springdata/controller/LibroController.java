package com.company.springdata.controller;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    LibroRepository repository;

    @RequestMapping(value = "buscartodos", params = "orden")
    public String buscarTodos(Model modelo, @RequestParam(name = "orden", defaultValue = "isbn") String orden) {

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

    @RequestMapping(value = "buscartodos")
    public String buscarTodos(Model modelo) {
        return buscarTodos(0, 8, modelo);
    }

    @RequestMapping(value = "buscartodos", params = {"pagina", "size"})
    public String buscarTodos(@RequestParam(name = "pagina", defaultValue = "0") int pagina,
                              @RequestParam(name = "size", defaultValue = "8") int size, Model model) {

        Iterable<Libro> libros = repository.findAll(PageRequest.of(pagina, size));
        model.addAttribute("libros", libros);
        model.addAttribute("pagina", pagina);
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

    @RequestMapping(value = "buscartodos", params = {"titulo", "orden"})
    public String buscarTodosPorTitulo(String titulo, String orden, Model modelo){
        Iterable<Libro> libros = repository.findByTitulo(titulo, Sort.by(orden));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("titulo", titulo);
        return "libros";
    }

    @RequestMapping(value = "buscartodos", params = {"autor", "orden"})
    public String buscarTodosPorAutor(String autor, String orden, Model modelo) {
        Iterable<Libro> libros = repository.findByAutor(autor, Sort.by(orden));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("autor", autor);
        return "libros";
    }

    @RequestMapping(value = "buscartodos", params = {"titulo", "autor", "orden"})
    public String buscarTodosPorTituloyAutor(String titulo, String autor, String orden, Model modelo) {
        Iterable<Libro> libros = repository.findByTituloAndAutor(titulo, autor, Sort.by(orden));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("autor", autor);

        return "libros";
    }
}
