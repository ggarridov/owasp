/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a4xxe.seguro.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.tutorial.owasp.a4xxe.seguro.dao.LibroDao;
import cl.tutorial.owasp.a4xxe.seguro.dominio.ErrorOut;
import cl.tutorial.owasp.a4xxe.seguro.dominio.Libro;
import cl.tutorial.owasp.a4xxe.seguro.dominio.ListaLibros;

/**
 * @author gerardo
 *
 */
@RestController
@RequestMapping("/seguro")
public class LibroWsSeguroController {

	@Autowired
	private LibroDao libroDao;

	@PostMapping(value = "/libro", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> crearLibro(@RequestBody final Libro libro) {
		if (libro == null || libro.getAutor() == null || libro.getTitulo() == null) {
			return ResponseEntity.ok(new ErrorOut("Libro invalido"));
		}
		libroDao.save(libro);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}

	@GetMapping(value = "/libro", produces = MediaType.APPLICATION_XML_VALUE, headers = "Accept=application/xml")
	public ResponseEntity<?> traerLibros() {
		ListaLibros libros = new ListaLibros();
		libros.setLibros(libroDao.findAll());
		return ResponseEntity.ok(libros);
	}

}