/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.modelo.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;

/**
 * @author gerardo
 *
 */
public class UsuarioApp extends User {

	private long id;
	private String username;
	private String password;

	/**
	 * @param username
	 * @param password
	 * @param id
	 * @param roles
	 */
	private UsuarioApp(final String username, final String password, final long id,
			final List<GrantedAuthority> roles) {
		super(username, password, roles);
		this.username = username;
		this.password = password;
		this.id = id;
	}

	/**
	 * @return the username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final long id) {
		this.id = id;
	}

	public static UsuarioApp getInstancia(final Usuario usuario, final List<GrantedAuthority> roles) {
		return new UsuarioApp(usuario.getUsername(), usuario.getPassword(), usuario.getId(), roles);
	}
}
