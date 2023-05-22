package com.company.springdata.controller;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

    @RequestMapping(value = "buscartodos")
    public String buscarTodos(@RequestParam(name = "orden", defaultValue = "fecha") String orden,
                              @RequestParam(name = "pagina", defaultValue = "0") int pagina,
                              @RequestParam(name = "size", defaultValue = "8") int size,
                              Model model) {
        Iterable<Libro> libros = repository.findAll(PageRequest.of(pagina, size, Sort.by(orden)));
        model.addAttribute("libros", libros);
        model.addAttribute("pagina", pagina);
        return "libros";
    }

    @RequestMapping(value = "buscarTodos", params = {"titulo", "orden", "pagina", "size"})
    public String buscarTodosPorTitulo(@RequestParam(name = "titulo") String titulo,
                                       @RequestParam(name = "orden") String orden,
                                       @RequestParam(name = "pagina", defaultValue = "0") int pagina,
                                       @RequestParam(name = "size", defaultValue = "8") int size,
                                       Model modelo) {
        Iterable<Libro> libros = repository.findByTitulo(titulo, PageRequest.of(pagina, size, Sort.by(orden)));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("pagina", pagina);
        return "libros";
    }

    @RequestMapping(value = "buscarTodos", params = {"autor", "orden", "pagina", "size"})
    public String buscarTodosPorAutor(@RequestParam(name = "autor") String autor,
                                      @RequestParam(name = "orden") String orden,
                                      @RequestParam(name = "pagina", defaultValue = "0") int pagina,
                                      @RequestParam(name = "size", defaultValue = "8") int size,
                                      Model modelo) {
        Iterable<Libro> libros = repository.findByAutor(autor, PageRequest.of(pagina, size, Sort.by(orden)));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("autor", autor);
        modelo.addAttribute("pagina", pagina);
        return "libros";
    }

    @RequestMapping(value = "buscarTodos")
    public String buscarTodosPorTituloyAutor(String titulo, String autor, String orden,
                                             @RequestParam(name = "pagina", defaultValue = "0") int pagina,
                                             @RequestParam(name = "size", defaultValue = "8") int size,
                                             Model modelo) {
        Libro libro = new Libro();

        if (titulo != null) {
            libro.setTitulo(titulo);
        }

        if (autor != null) {
            libro.setAutor(autor);
        }

        Example<Libro> libroExample = Example.of(libro);

        Iterable<Libro> libros = repository.findAll(libroExample, PageRequest.of(pagina, size, Sort.by(orden)));
        modelo.addAttribute("libros", libros);
        modelo.addAttribute("titulo", titulo);
        modelo.addAttribute("autor", autor);
        modelo.addAttribute("pagina", pagina);
        return "libros";
    }

}
