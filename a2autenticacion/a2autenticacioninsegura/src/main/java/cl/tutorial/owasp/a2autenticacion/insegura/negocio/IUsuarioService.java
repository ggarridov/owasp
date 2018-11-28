/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.negocio;

import java.util.List;

import cl.tutorial.owasp.a2autenticacion.insegura.dominio.UsuarioApp;
import cl.tutorial.owasp.a2autenticacion.insegura.modelo.enums.RolesEnum;

/**
 * @author gerardo
 *
 */
public interface IUsuarioService {

	UsuarioApp buscarUsuario(String userName);

	List<RolesEnum> buscarRoles(UsuarioApp usuarioApp);

}
