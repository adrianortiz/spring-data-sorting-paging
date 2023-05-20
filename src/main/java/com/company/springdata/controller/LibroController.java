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

    @RequestMapping(value = "buscarTodos", params = {"titulo", "orden", "pagina", "size"})
    public String buscarTodosPorTitulo(String titulo, String orden,
                                       @RequestParam(name = "pagina", defaultValue = "0") int pagina,
                                       @RequestParam(name = "size", defaultValue = "8") int size, Model modelo) {
        Iterable<Libro> libros = repository.findByTitulo(titulo, PageRequest.of(pagina, size, Sort.by(orden)));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("pagina", pagina);
        return "libros";
    }

}
