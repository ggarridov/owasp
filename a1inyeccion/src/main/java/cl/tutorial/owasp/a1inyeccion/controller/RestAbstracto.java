/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.tutorial.owasp.a1inyeccion.dominio.ErrorOut;
import cl.tutorial.owasp.a1inyeccion.dominio.Usuario;
import cl.tutorial.owasp.a1inyeccion.servicio.IUsuarioService;

/**
 * <p>
 * Clase abstracta para servicio.
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
public abstract class RestAbstracto {

	protected abstract Logger getLogger();

	protected abstract IUsuarioService getUsuarioService();

	protected ResponseEntity<?> obtenerUsuarioJpa(final String username) {
		getLogger().info("Buscando usuario por nombre JPA: {}", username);
		return salida(getUsuarioService().buscarPorUsernameJpa(username), username);
	}

	protected ResponseEntity<?> obtenerUsuarioJdbc(final String username) {
		getLogger().info("Buscando usuario por nombre JDBC: {}", username);
		return salida(getUsuarioService().buscarPorUsernameJdbc(username), username);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ResponseEntity<?> salida(final List<Usuario> usuarios, final String username) {
		if (usuarios == null || usuarios.isEmpty()) {
			getLogger().error("Usuario {} no encontrado", username);
			return new ResponseEntity(new ErrorOut(String.format("El usuario %s no existe", username)),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
}
