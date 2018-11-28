/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.negocio;

import java.util.List;

import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;
import cl.tutorial.owasp.a5perdidacontrol.modelo.enums.RolesEnum;

/**
 * @author gerardo
 *
 */
public interface IUsuarioService {

	Usuario buscarUsuario(String username);

	List<RolesEnum> buscarRoles(String username);

	void marcarIntentoError(final String username);

	void reiniciarIntentoError(final String username, final String ip);

}
