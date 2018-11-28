/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.modelo.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(name = "intentos")
	private int intentos;
	@Column(name = "ultimoAcceso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcceso;
	@Column(name = "ipUltimoAcceso")
	private String ipUltimoAcceso;

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
	 * @return
	 */
	public int getIntentos() {
		return intentos;
	}

	/**
	 * @param intentos
	 */
	public void setIntentos(final int intentos) {
		this.intentos = intentos;
	}

	/**
	 * @return the ultimoAcceso
	 */
	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	/**
	 * @param ultimoAcceso the ultimoAcceso to set
	 */
	public void setUltimoAcceso(final Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	/**
	 * @return the ipUltimoAcceso
	 */
	public String getIpUltimoAcceso() {
		return ipUltimoAcceso;
	}

	/**
	 * @param ipUltimoAcceso the ipUltimoAcceso to set
	 */
	public void setIpUltimoAcceso(final String ipUltimoAcceso) {
		this.ipUltimoAcceso = ipUltimoAcceso;
	}

}
