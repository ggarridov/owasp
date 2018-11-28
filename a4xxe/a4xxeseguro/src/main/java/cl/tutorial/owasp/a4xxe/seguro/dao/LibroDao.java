/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a4xxe.seguro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a4xxe.seguro.dominio.Libro;

/**
 * @author gerardo
 *
 */
@Repository
public interface LibroDao extends JpaRepository<Libro, Long> {

}