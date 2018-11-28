/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a4xxe.inseguro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cl.tutorial.owasp.a4xxe.inseguro.dto.Libro;

/**
 * @author gerardo
 *
 */
@Path("/libros")
public class RestLibros {

	static final Map<Integer, Libro> libros = new ConcurrentHashMap<Integer, Libro>();
	static volatile int currentId = 0;

	static {
		Libro libro = new Libro();
		libro.setId(currentId);
		libro.setTitulo("Java puzzlers");
		libro.setAutor("Joshua Bloch");
		libros.put(currentId++, libro);
		libro = new Libro();
		libro.setId(currentId);
		libro.setTitulo("Java concurrency in practice");
		libro.setAutor("Brian Goetz");
		libros.put(currentId++, libro);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public void createLibro(final Libro libro) {
		libro.setId(currentId);
		libros.put(currentId++, libro);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Libro> retrieveAllLibros() {
		List<Libro> result = new ArrayList<Libro>();
		for (Map.Entry<Integer, Libro> entry : libros.entrySet()) {
			result.add(entry.getValue());
		}
		return result;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Libro retrieveLibro(@PathParam("id") final int id) {
		return libros.get(id);
	}

	@DELETE
	@Path("{id}")
	public void deleteLibro(@PathParam("id") final int id) {
		libros.remove(id);
	}

}
