/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a4xxe.seguro.dominio;

import java.util.List;

/**
 * @author gerardo
 *
 */
public class ListaLibros {

	private List<Libro> libros;

	/**
	 * @return the libros
	 */
	public List<Libro> getLibros() {
		return libros;
	}

	/**
	 * @param libros the libros to set
	 */
	public void setLibros(final List<Libro> libros) {
		this.libros = libros;
	}

}
