/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;

/**
 * @author gerardo
 *
 */
@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}
