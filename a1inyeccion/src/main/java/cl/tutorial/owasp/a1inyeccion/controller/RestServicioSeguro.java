/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.tutorial.owasp.a1inyeccion.servicio.IUsuarioService;

/**
 * <p>
 * Clase que expone servicios rest que permiten injeccion de SQL
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/seguro")
public class RestServicioSeguro extends RestAbstracto {

	public static final Logger LOGGER = LoggerFactory.getLogger(RestServicioSeguro.class);

	@Autowired
	private IUsuarioService usuarioServiceSeguro;

	@GetMapping(value = "/usuario/jpa/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getInseguroJpa(@PathVariable("username") final String username) {
		return obtenerUsuarioJpa(username);
	}

	@GetMapping(value = "/usuario/jdbc/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getInseguroJdbc(@PathVariable("username") final String username) {
		return obtenerUsuarioJdbc(username);
	}

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}

	@Override
	protected IUsuarioService getUsuarioService() {
		return usuarioServiceSeguro;
	}

}
