package com.company.springdata.metamodel;

import com.company.springdata.entity.Capitulo;
import com.company.springdata.entity.Libro;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Capitulo.class)
public abstract class Capitulo_ {

	public static volatile SingularAttribute<Capitulo, Integer> paginas;
	public static volatile SingularAttribute<Capitulo, Libro> libro;
	public static volatile SingularAttribute<Capitulo, String> titulo;

	public static final String PAGINAS = "paginas";
	public static final String LIBRO = "libro";
	public static final String TITULO = "titulo";

}

