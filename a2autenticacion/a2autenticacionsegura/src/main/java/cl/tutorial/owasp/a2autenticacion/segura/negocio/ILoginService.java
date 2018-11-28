/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.negocio;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.entidad.IntentoFallido;

/**
 * @author gerardo
 *
 */
public interface ILoginService {

	void loginFallido(IntentoFallido intentoFallido);

	void loginExisto(final String username, final String ip);

	boolean isUsuarioBloqueado(final String username);

	boolean isIpBloqueado(final String ip);

}
