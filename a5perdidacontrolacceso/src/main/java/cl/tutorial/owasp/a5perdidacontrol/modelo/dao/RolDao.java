/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.modelo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Rol;

/**
 * @author gerardo
 *
 */
@Repository
public interface RolDao extends JpaRepository<Rol, Long> {

	List<Rol> findByUsername(String username);

}
