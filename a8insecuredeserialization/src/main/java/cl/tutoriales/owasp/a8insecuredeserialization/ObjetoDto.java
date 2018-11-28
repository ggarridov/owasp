/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutoriales.owasp.a8insecuredeserialization;

import java.io.Serializable;

/**
 * @author gerardo
 *
 */
public class ObjetoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public int a;
	public String b;

	public ObjetoDto(final int a, final String b) {
		this.a = a;
		this.b = b;
	}

	public void ejecutaReglaNegocio() {
		System.out.println("hola lala");
	}

}
