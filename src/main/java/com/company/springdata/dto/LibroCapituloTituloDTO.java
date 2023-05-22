package com.company.springdata.dto;

import java.util.Objects;

public class LibroCapituloTituloDTO {

    private String tituloLibro;
    private String tituloCapitulo;

    public LibroCapituloTituloDTO() {
    }

    public LibroCapituloTituloDTO(String tituloLibro, String tituloCapitulo) {
        this.tituloLibro = tituloLibro;
        this.tituloCapitulo = tituloCapitulo;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getTituloCapitulo() {
        return tituloCapitulo;
    }

    public void setTituloCapitulo(String tituloCapitulo) {
        this.tituloCapitulo = tituloCapitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroCapituloTituloDTO that = (LibroCapituloTituloDTO) o;
        return Objects.equals(tituloLibro, that.tituloLibro) && Objects.equals(tituloCapitulo, that.tituloCapitulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tituloLibro, tituloCapitulo);
    }
}
