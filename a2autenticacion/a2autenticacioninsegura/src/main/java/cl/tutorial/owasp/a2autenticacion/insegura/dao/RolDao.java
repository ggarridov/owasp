/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a2autenticacion.insegura.dominio.Rol;

/**
 * @author gerardo
 *
 */
@Repository
public interface RolDao extends JpaRepository<Rol, Long> {

	List<Rol> findByUsername(String username);

}
