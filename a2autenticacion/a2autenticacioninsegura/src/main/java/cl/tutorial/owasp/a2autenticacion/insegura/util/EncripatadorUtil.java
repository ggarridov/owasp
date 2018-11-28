/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author gerardo
 *
 */
@Component
public class EncripatadorUtil {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String encrytePassword(final String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}
}
