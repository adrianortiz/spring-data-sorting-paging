package com.company.springdata.metamodel;

import com.company.springdata.entity.Capitulo;
import com.company.springdata.entity.Libro;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Libro.class)
public abstract class Libro_ {

	public static volatile SingularAttribute<Libro, Date> fecha;
	public static volatile ListAttribute<Libro, Capitulo> capitulos;
	public static volatile SingularAttribute<Libro, Double> precio;
	public static volatile SingularAttribute<Libro, String> isbn;
	public static volatile SingularAttribute<Libro, String> titulo;
	public static volatile SingularAttribute<Libro, String> autor;

	public static final String FECHA = "fecha";
	public static final String CAPITULOS = "capitulos";
	public static final String PRECIO = "precio";
	public static final String ISBN = "isbn";
	public static final String TITULO = "titulo";
	public static final String AUTOR = "autor";

}

