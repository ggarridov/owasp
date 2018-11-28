/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a5perdidacontrol.modelo.dto.UsuarioApp;
import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;
import cl.tutorial.owasp.a5perdidacontrol.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a5perdidacontrol.negocio.IUsuarioService;

/**
 * @author gerardo
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;
	public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		LOGGER.debug("Iniciando autenticacion usuario {}", username);
		Usuario usuario = buscarUsuario(username);
		List<GrantedAuthority> roles = buscarRoles(username);
		LOGGER.debug("Fin proceso autenticacion {}", username);
		return UsuarioApp.getInstancia(usuario, roles);
	}

	private List<GrantedAuthority> buscarRoles(final String username) {
		LOGGER.debug("Buscando roles para usuario {}", username);
		List<RolesEnum> roles = usuarioService.buscarRoles(username);
		if (roles == null) {
			LOGGER.error("Usuario {} sin roles", username);
			return Collections.emptyList();
		}
		List<GrantedAuthority> rolesSpring = new ArrayList<>();
		roles.stream().forEach(rol -> rolesSpring.add(new SimpleGrantedAuthority(rol.name())));
		LOGGER.debug("Roles para usuario {} encontrados {}", username, roles.size());
		return rolesSpring;
	}

	private Usuario buscarUsuario(final String username) {
		LOGGER.debug("Buscando Usuario {} ", username);
		Usuario usuario = usuarioService.buscarUsuario(username);
		if (usuario == null) {
			LOGGER.error("Usuario {} no existe", username);
			throw new UsernameNotFoundException(String.format("Usuario %s no encontrado", username));
		}
		LOGGER.debug("Usuario {} encontrado", username);
		return usuario;
	}

}
