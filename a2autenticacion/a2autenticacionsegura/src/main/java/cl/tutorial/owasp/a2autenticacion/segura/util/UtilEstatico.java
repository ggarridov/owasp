/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.util;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author gerardo
 *
 */
public class UtilEstatico {

	public static String getIpRequest(final HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}

	public static String requestToString(final HttpServletRequest request) {
		ServletInputStream requestInputStream;
		try {
			requestInputStream = request.getInputStream();
		} catch (IOException e) {
			return "";
		}
		byte[] requestByteArrya = new byte[request.getContentLength()];
		int requestVal = -1;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((requestVal = requestInputStream.read(requestByteArrya)) != -1) {
				for (int i = 0; i < requestVal; i++) {
					stringBuilder.append(Character.toString((char) requestByteArrya[i]));
				}
			}
		} catch (IOException e) {
			return "";
		}
		return stringBuilder.toString();
	}
}
