/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.repositorio;

import java.util.List;

import cl.tutorial.owasp.a1inyeccion.dominio.Usuario;

/**
 * <p>
 * Interface que provee acceso comun a implementacions de DAO Usuario
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
public interface IUsuarioDao {

	List<Usuario> findByUsernameJpa(String username);

	List<Usuario> findByUsernameJdbc(final String username);

}
