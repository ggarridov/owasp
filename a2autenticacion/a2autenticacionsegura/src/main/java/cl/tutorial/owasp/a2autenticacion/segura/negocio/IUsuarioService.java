/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.negocio;

import java.util.List;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.dto.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.RolesEnum;

/**
 * @author gerardo
 *
 */
public interface IUsuarioService {

	UsuarioApp buscarUsuario(String userName);

	List<RolesEnum> buscarRoles(UsuarioApp usuarioApp);

	void marcarIntentoError(final String username);

	void reiniciarIntentoError(final String username, final String ip);

}
