/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Entidad tabla usuario
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
@Entity
@Table(name = "USUARIO")
@NamedQueries({
		@NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u where u.username like :username") })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	public static final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);

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

	/**
	 * @param resultSet
	 * @return
	 */
	public static Usuario getInstancia(final ResultSet resultSet) {
		Usuario retorno = new Usuario();
		try {
			retorno.setId(resultSet.getLong("id"));
			retorno.setUsername(resultSet.getString("username"));
			retorno.setUsername(resultSet.getString("password"));
		} catch (SQLException e) {
			LOGGER.error("No se logra cargar usuario {}", e.getMessage());
		}
		return retorno;
	}

}
