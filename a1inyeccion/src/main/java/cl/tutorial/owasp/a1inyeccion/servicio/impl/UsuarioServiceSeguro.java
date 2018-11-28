/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a1inyeccion.dominio.Usuario;
import cl.tutorial.owasp.a1inyeccion.repositorio.IUsuarioDao;
import cl.tutorial.owasp.a1inyeccion.servicio.IUsuarioService;

/**
 * <p>
 * implementa {@link IUsuarioService}
 * </p>
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
@Service("usuarioServiceSeguro")
public class UsuarioServiceSeguro implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDaoSeguro;

	@Override
	public List<Usuario> buscarPorUsernameJpa(final String username) {
		return usuarioDaoSeguro.findByUsernameJpa(username);
	}

	@Override
	public List<Usuario> buscarPorUsernameJdbc(final String username) {
		return usuarioDaoSeguro.findByUsernameJdbc(username);
	}

}
