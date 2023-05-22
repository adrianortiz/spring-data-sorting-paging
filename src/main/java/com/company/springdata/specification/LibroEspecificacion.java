package com.company.springdata.specification;

import com.company.springdata.entity.Libro;
import com.company.springdata.metamodel.Libro_;
import org.springframework.data.jpa.domain.Specification;

public class LibroEspecificacion {
    public static Specification<Libro> tituloStartsWith(final String texto) {
        return ((root, query, builder) -> {
           return builder.like(builder.lower(root.<String>get(Libro_.titulo)), texto + "%");
        });
    }
}
