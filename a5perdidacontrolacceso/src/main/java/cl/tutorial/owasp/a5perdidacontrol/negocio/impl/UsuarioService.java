/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.negocio.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a5perdidacontrol.modelo.dao.RolDao;
import cl.tutorial.owasp.a5perdidacontrol.modelo.dao.UsuarioDao;
import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;
import cl.tutorial.owasp.a5perdidacontrol.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a5perdidacontrol.negocio.IUsuarioService;

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
	public Usuario buscarUsuario(final String username) {
		Usuario usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			return null;
		}
		return usuario;
	}

	@Override
	public List<RolesEnum> buscarRoles(final String username) {
		return rolDao.findByUsername(username).stream().map((rol) -> RolesEnum.valueOf(rol.getRol()))
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
