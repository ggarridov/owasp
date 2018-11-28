/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a4xxe.seguro.dominio;

/**
 * @author gerardo
 *
 */
public class ErrorOut {

	private final String mensaje;

	public ErrorOut() {
		this.mensaje = "sin mensaje";
	}

	public ErrorOut(final String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}
