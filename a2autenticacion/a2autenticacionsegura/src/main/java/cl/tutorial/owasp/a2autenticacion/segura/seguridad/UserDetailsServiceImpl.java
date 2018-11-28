/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.dto.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.ILoginService;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.IUsuarioService;
import cl.tutorial.owasp.a2autenticacion.segura.util.UtilEstatico;

/**
 * <p>
 * Clase que implementa logica de autenticacion previo a determinar fallo o
 * error
 * </p>
 *
 * @author gerardo
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ILoginService loginService;

	@Autowired
	private HttpServletRequest request;

	public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		String ip = UtilEstatico.getIpRequest(request);
		if (loginService.isIpBloqueado(ip)) {
			LOGGER.error("Ip {} bloqueada por intentos fallidos", ip);
			throw new LockedException("IP Bloqueada");
		}
		if (loginService.isUsuarioBloqueado(userName)) {
			LOGGER.error("Usuario {} bloqueado por intentos fallidos", userName);
			throw new LockedException("Usuario Bloqueada");
		}
		LOGGER.info("Inicia proceso de autenticacion usuario");
		UsuarioApp usuarioApp = buscarUsuario(userName);
		List<GrantedAuthority> roles = buscarRoles(usuarioApp);
		return generarRetorno(usuarioApp, roles);
	}

	private List<GrantedAuthority> buscarRoles(final UsuarioApp usuarioApp) {
		LOGGER.info("Buscando roles para usuario {} ", usuarioApp.getUsername());
		List<RolesEnum> roleNames = usuarioService.buscarRoles(usuarioApp);
		if (roleNames == null) {
			LOGGER.info("No existen roles asume vacio");
			return Collections.emptyList();
		}

		List<GrantedAuthority> grantList = new ArrayList<>();
		roleNames.stream().forEach(rol -> grantList.add(new SimpleGrantedAuthority(rol.name())));
		return grantList;
	}

	private UsuarioApp buscarUsuario(final String userName) {
		LOGGER.info("Buscando usuario {} ", userName);
		UsuarioApp usuarioApp = usuarioService.buscarUsuario(userName);
		if (usuarioApp == null) {
			LOGGER.error("Usuario {} no fue encontrado", userName);
			throw new UsernameNotFoundException("Usuario no existe");
		}
		return usuarioApp;
	}

	private UserDetails generarRetorno(final UsuarioApp usuarioApp, final List<GrantedAuthority> roles) {
		return new User(usuarioApp.getUsername(), usuarioApp.getPassword(), roles);
	}
}
