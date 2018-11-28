/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.repositorio.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cl.tutorial.owasp.a1inyeccion.dominio.Usuario;
import cl.tutorial.owasp.a1inyeccion.repositorio.DaoJdbc;
import cl.tutorial.owasp.a1inyeccion.repositorio.IUsuarioDao;

/**
 * <p>
 * implementa {@link IUsuarioDao}
 * </p>
 * .
 *
 * @author gerardo.garrido
 * @version 1.0
 *
 */
@Repository("usuarioDaoInseguro")
public class UsuarioDaoInseguro extends DaoJdbc implements IUsuarioDao {

	public static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDaoInseguro.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> findByUsernameJpa(final String username) {
		Query query = em.createNativeQuery(getQuery(username), Usuario.class);
		return query.getResultList();
	}

	@Override
	public List<Usuario> findByUsernameJdbc(final String username) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.createStatement();
			List<Usuario> usuarios = new ArrayList<>();
			resultSet = statement.executeQuery(getQuery(username));
			while (resultSet.next()) {
				usuarios.add(Usuario.getInstancia(resultSet));
			}
			return usuarios;
		} catch (SQLException e) {
			LOGGER.error("Error usando jdbc");
			return Collections.emptyList();
		} finally {
			cerrarConexiones(connection, statement, resultSet);
		}

	}

	/**
	 * @param username
	 * @return
	 */
	private String getQuery(final String username) {
		final String queryInseguraFormat = "Select * from usuario u where u.username like '%%%s%%'";
		final String query = String.format(queryInseguraFormat, username);
		LOGGER.info("Ejecutando query insegura JPA : {}", query);
		return query;
	}

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
}
