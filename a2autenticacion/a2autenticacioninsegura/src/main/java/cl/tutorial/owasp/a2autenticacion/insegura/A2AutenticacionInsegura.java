/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * Clase inicial spring boot.
 * </p>
 *
 * @author gerardo.garrido
 * @version 1.0
 */
@SpringBootApplication
public class A2AutenticacionInsegura {

	public static void main(final String[] args) {
		SpringApplication.run(A2AutenticacionInsegura.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
