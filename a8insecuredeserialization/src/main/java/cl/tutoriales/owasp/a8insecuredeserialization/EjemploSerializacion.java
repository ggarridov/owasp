/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutoriales.owasp.a8insecuredeserialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author gerardo
 *
 */
public class EjemploSerializacion {
	public static void main(final String[] args) {
		ObjetoDto objetoDtoSerial = new ObjetoDto(1, "demo");
		String filename = "/u/turoriales/owasp/material/file.ser";

		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(objetoDtoSerial);

			out.close();
			file.close();

			System.out.println("objeto serializado");

		}

		catch (IOException ex) {
			System.out.println("Error");
		}

		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			ObjetoDto objetoDtoDes = (ObjetoDto) in.readObject();

			in.close();
			file.close();

			System.out.println("Objeto deserializado");
			System.out.println("a = " + objetoDtoDes.a);
			System.out.println("b = " + objetoDtoDes.b);
		}

		catch (IOException ex) {
			System.out.println("Error");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("Class not found");
		}

	}
}
