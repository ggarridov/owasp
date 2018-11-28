/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.negocio.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a2autenticacion.insegura.dao.RolDao;
import cl.tutorial.owasp.a2autenticacion.insegura.dao.UsuarioDao;
import cl.tutorial.owasp.a2autenticacion.insegura.dominio.Usuario;
import cl.tutorial.owasp.a2autenticacion.insegura.dominio.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.insegura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.insegura.negocio.IUsuarioService;

/**
 * @author gerardo
 *
 */
@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private RolDao rolDao;

	@Override
	public UsuarioApp buscarUsuario(final String userName) {
		Usuario usuario = usuarioDao.findByUsername(userName);
		if (usuario == null) {
			return null;
		}
		return UsuarioApp.getInstancia(usuario);
	}

	@Override
	public List<RolesEnum> buscarRoles(final UsuarioApp usuarioApp) {
		return rolDao.findByUsername(usuarioApp.getUsername()) //
				.stream().map((rol) -> RolesEnum.valueOf(rol.getRol())) //
				.collect(Collectors.toList());
	}

}
