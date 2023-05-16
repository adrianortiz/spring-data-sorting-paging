package com.company.springdata.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "capitulos")
public class Capitulo {

    @Id
    private String titulo;
    private int paginas;

    @ManyToOne
    @JoinColumn(name = "libros_isbn")
    private Libro libro;

    public Capitulo() {
    }

    public Capitulo(String titulo) {
        this.titulo = titulo;
    }

    public Capitulo(String titulo, int paginas) {
        this.titulo = titulo;
        this.paginas = paginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capitulo capitulo = (Capitulo) o;
        return Objects.equals(titulo, capitulo.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }
}
