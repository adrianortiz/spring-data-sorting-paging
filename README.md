# Spring Data - Sorting and Paging
Aplicación Web basada en Spring Boot para el manejo del catálogo de Libros

**Características:**
* Diseño web
  * Responsive Design
  * Dark mode
* Buscar Libro por:
  * Título
  * Autor
* Ordenar Libro por:
  * Isbn
  * Título
  * Autor
  * Precio
  * Fecha
* Páginado de Libros
* CRUD Rest Libros
* y más

Actualmente este proyecto no está terminado y posiblemente presente anomalias en su código fuente, por ende, se recomienda no usarlo en un ambiente productivo.
**Eres libre de usar y/o distribuir este producto.**

### Requerimientos
* Java 11
* Spring Framework
* Maven

### ¿Cómo instalar?

* Descargar el proyecto dentro de un directorio especifico
* Entrar al directorio del proyecto desde el Explorador de Archivos/Finder

    - Añadir la configuración de la conexión de la BD al archivo **SpringdataApplication**
    - Crear Base de Datos en MySQL (**core**)
    - Cargar Script/Base de Datos: Tabla [`squema.sql - /resources/squema.sql`](https://github.com/adrianortiz/spring-data-sorting-paging/blob/main/src/main/resources/squema.sql)
    - Cargar Script/Base de Datos: Datos [`data.sql - /resources/data.sql`](https://github.com/adrianortiz/spring-data-sorting-paging/blob/main/src/main/resources/data.sql)


* Entrar al directorio del proyecto desde Consola/Terminal

    - Instalar dependencias: `mvn clean`
    - Instalar empaquetar: `mvn install`
    - Desplegar
  


## Imagenes del proyecto
![Pantalla principal](https://github.com/adrianortiz/spring-data-sorting-paging/blob/main/src/main/resources/static/images/screenshot-01.png)
![Pantalla principa](https://github.com/adrianortiz/spring-data-sorting-paging/blob/main/src/main/resources/static/images/screenshot-02.png)


## Spring Framework

### License

The Spring framework is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)