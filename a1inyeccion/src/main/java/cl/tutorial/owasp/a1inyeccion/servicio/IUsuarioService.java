/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.servicio;

import java.util.List;

import cl.tutorial.owasp.a1inyeccion.dominio.Usuario;

/**
 * <p>
 * interface que permite acceder a mentodos abstractos
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
public interface IUsuarioService {

	List<Usuario> buscarPorUsernameJpa(final String username);

	List<Usuario> buscarPorUsernameJdbc(final String username);

}
