/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.dominio;

/**
 * <p>
 * Objeto generico para ser retornado por servicios en caso de error.
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
public class ErrorOut {

	private final String mensaje;

	public ErrorOut(final String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}
