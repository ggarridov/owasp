/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.dominio;

/**
 * @author gerardo
 *
 */
public class UsuarioApp {

	private String username;
	private String password;

	/**
	 * @param username
	 * @param password
	 */
	private UsuarioApp(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
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
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	public static UsuarioApp getInstancia(final Usuario usuario) {
		return new UsuarioApp(usuario.getUsername(), usuario.getPassword());
	}

}
