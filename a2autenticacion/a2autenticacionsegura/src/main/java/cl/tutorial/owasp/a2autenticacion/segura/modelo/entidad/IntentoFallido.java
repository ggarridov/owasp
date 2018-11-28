/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.modelo.entidad;

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
import javax.servlet.http.HttpServletRequest;

import cl.tutorial.owasp.a2autenticacion.segura.util.UtilEstatico;

/**
 * @author gerardo
 *
 */
@Entity
@Table(name = "INTENTO_FALLIDO")
@NamedQueries({
		@NamedQuery(name = "IntentoFallido.findByUsername", query = "SELECT i FROM IntentoFallido i where i.username like :username"),
		@NamedQuery(name = "IntentoFallido.findByIp", query = "SELECT i FROM IntentoFallido i where i.username like :username") })
public class IntentoFallido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "ip")
	private String ip;
	@Column(name = "parametrosRequest")
	private String parametrosRequest;
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Column(name = "mensajeExcepcion", length = 2000)
	private String mensajeExcepcion;

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
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * @return the parametrosRequest
	 */
	public String getParametrosRequest() {
		return parametrosRequest;
	}

	/**
	 * @param parametrosRequest the parametrosRequest to set
	 */
	public void setParametrosRequest(final String parametrosRequest) {
		this.parametrosRequest = parametrosRequest;
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
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	public static IntentoFallido getInstancia(final HttpServletRequest request) {
		IntentoFallido intentoFallido = new IntentoFallido();
		intentoFallido.setIp(UtilEstatico.getIpRequest(request));
		String usuario = request.getParameter("username");
		if (usuario != null) {
			intentoFallido.setUsername(usuario);
		}
		String password = request.getParameter("password");
		if (password != null) {
			intentoFallido.setPassword(password);
		}
		intentoFallido.setParametrosRequest(UtilEstatico.requestToString(request));
		intentoFallido.setFecha(new Date());
		return intentoFallido;
	}

}
