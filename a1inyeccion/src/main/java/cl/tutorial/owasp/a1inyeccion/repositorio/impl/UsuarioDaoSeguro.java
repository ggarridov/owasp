/**
 * Copyright 2017 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.repositorio.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
@Repository("usuarioDaoSeguro")
public class UsuarioDaoSeguro extends DaoJdbc implements IUsuarioDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDaoSeguro.class);

	@Override
	public List<Usuario> findByUsernameJpa(final String username) {
		final String paramLike = "%" + username + "%";
		TypedQuery<Usuario> query = em.createNamedQuery("Usuario.findByUsername", Usuario.class);
		query.setParameter("username", paramLike);
		LOGGER.info("Query segura JPA");
		return query.getResultList();
	}

	@Override
	public List<Usuario> findByUsernameJdbc(final String username) {
		final String query = "Select * from usuario u where u.username like ?";
		Connection connection = null;
		// Objeto inficiente
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			statement = connection.prepareCall(query);
			statement.setString(1, username);
			List<Usuario> usuarios = new ArrayList<>();
			resultSet = statement.executeQuery();
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

	@Override
	protected Logger getLogger() {
		return LOGGER;
	}
}
