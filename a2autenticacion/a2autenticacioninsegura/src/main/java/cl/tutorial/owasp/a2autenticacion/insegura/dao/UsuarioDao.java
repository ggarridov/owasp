/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a2autenticacion.insegura.dominio.Usuario;

/**
 * @author gerardo
 *
 */
@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}
