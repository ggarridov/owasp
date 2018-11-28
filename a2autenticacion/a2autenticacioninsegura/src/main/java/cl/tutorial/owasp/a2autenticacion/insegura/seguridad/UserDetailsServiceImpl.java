/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a2autenticacion.insegura.dominio.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.insegura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.insegura.negocio.IUsuarioService;

/**
 * @author gerardo
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;
	public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private static int contador;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		LOGGER.debug("Iniciando autenticacion usuario {}", username);
		UsuarioApp usuarioApp = buscarUsuario(username);
		List<GrantedAuthority> roles = buscarRoles(usuarioApp);
		LOGGER.debug("Fin proceso autenticacion {}", username);
		return generarRetorno(usuarioApp, roles);
	}

	private List<GrantedAuthority> buscarRoles(final UsuarioApp usuarioApp) {
		LOGGER.debug("Buscando roles para usuario {}", usuarioApp.getUsername());
		List<RolesEnum> roles = usuarioService.buscarRoles(usuarioApp);
		if (roles == null) {
			LOGGER.error("Usuario {} sin roles", usuarioApp.getUsername());
			return Collections.emptyList();
		}
		List<GrantedAuthority> rolesSpring = new ArrayList<>();
		roles.stream().forEach(rol -> rolesSpring.add(new SimpleGrantedAuthority(rol.name())));
		LOGGER.debug("Roles para usuario {} encontrados {}", usuarioApp.getUsername(), roles.size());
		return rolesSpring;
	}

	private UsuarioApp buscarUsuario(final String username) {
		LOGGER.debug("Buscando Usuario {} ", username);
		UsuarioApp usuarioApp = usuarioService.buscarUsuario(username);
		if (usuarioApp == null) {
			LOGGER.error("Usuario {} no existe", username);
			throw new UsernameNotFoundException(String.format("Usuario %s no encontrado", username));
		}
		LOGGER.debug("Usuario {} encontrado", username);
		return usuarioApp;
	}

	private UserDetails generarRetorno(final UsuarioApp usuarioApp, final List<GrantedAuthority> roles) {
		return new User(usuarioApp.getUsername(), usuarioApp.getPassword(), roles);
	}

}
