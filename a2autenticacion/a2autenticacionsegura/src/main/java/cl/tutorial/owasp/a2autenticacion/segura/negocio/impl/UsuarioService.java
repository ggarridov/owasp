/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.negocio.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.dao.RolDao;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.dao.UsuarioDao;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.dto.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.entidad.Usuario;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.IUsuarioService;

/**
 * <p>
 * Clase que mantiene info de usuario y roles
 * </p>
 *
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

	@Override
	public void marcarIntentoError(final String username) {
		if (username == null || username.isEmpty()) {
			return;
		}
		Usuario usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			return;
		}
		usuario.setIntentos(usuario.getIntentos() + 1);
		usuarioDao.save(usuario);
	}

	@Override
	public void reiniciarIntentoError(final String username, final String ip) {
		Usuario usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			return;
		}
		usuario.setIntentos(0);
		usuario.setIpUltimoAcceso(ip);
		usuario.setUltimoAcceso(new Date());
		usuarioDao.save(usuario);
	}
}
