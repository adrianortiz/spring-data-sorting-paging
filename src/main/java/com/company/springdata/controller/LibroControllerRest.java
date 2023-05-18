package com.company.springdata.controller;

import com.company.springdata.entity.Libro;
import com.company.springdata.repository.LibroRepository;
import com.company.springdata.response.LibroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/libros")
public class LibroControllerRest {

    @Autowired
    private LibroRepository repository;

    @GetMapping
    public List<Libro> consultarTodas() {
        Iterable<Libro> it = repository.findAll();
        List<Libro> libros = new ArrayList<Libro>();
        it.forEach(libros::add);
        return libros;
    }

    @GetMapping("/{isbn}")
    public Libro consultarUna(@PathVariable String isbn) {
        Optional<Libro> optional = repository.findById(isbn);
        Libro libro1 = null;
        if (optional.isPresent()) {
            libro1 = optional.get();
        }
        return libro1;
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity eliminar(@PathVariable String isbn) {
        try {
            repository.deleteById(isbn);
            return ResponseEntity.status(HttpStatus.OK).body(new LibroResponse("OK", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LibroResponse("Not found - " + e.getMessage(), "404"));
        }
    }

    @PostMapping
    public ResponseEntity crear(@RequestBody Libro libro) {
        try {
            repository.save(libro);
            return ResponseEntity.status(HttpStatus.OK).body(new LibroResponse("Created", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LibroResponse("Bad request - " + e.getMessage(), "400"));
        }
    }

    @PutMapping
    public ResponseEntity actualizar(@RequestBody Libro libro) {
        try {
            repository.save(libro);
            return ResponseEntity.status(HttpStatus.OK).body(new LibroResponse("Updated", "200"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LibroResponse("Bad request - " + e.getMessage(), "400"));
        }
    }

}
