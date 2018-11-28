/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a1inyeccion.repositorio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

/**
 * @author gerardo
 *
 */
public abstract class DaoJdbc {

	protected abstract Logger getLogger();

	protected void cerrarConexiones(final Connection connection, final Statement statement, final ResultSet resultSet) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			getLogger().error("Error cerrando connection");
		}

		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			getLogger().error("Error cerrando statement");
		}

		try {
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			getLogger().error("Error cerrando resultSet");
		}

	}
}
