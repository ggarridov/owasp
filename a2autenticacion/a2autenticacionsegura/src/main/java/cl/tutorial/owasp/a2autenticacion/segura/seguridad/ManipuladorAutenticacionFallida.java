/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.seguridad;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.entidad.IntentoFallido;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.ILoginService;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.IUsuarioService;

/**
 * <p>
 * Clase que se activa cuando la autenticacion es fallida
 * </p>
 *
 * @author gerardo
 *
 */
@Component
public class ManipuladorAutenticacionFallida implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private HttpServletRequest request;

	public static final Logger LOGGER = LoggerFactory.getLogger(ManipuladorAutenticacionFallida.class);

	@Override
	public void onApplicationEvent(final AuthenticationFailureBadCredentialsEvent event) {
		IntentoFallido intentoFallido = IntentoFallido.getInstancia(request);
		LOGGER.info("Autenticacion fallida, request {} ", intentoFallido.getParametrosRequest());
		loginService.loginFallido(intentoFallido);
		if (intentoFallido.getUsername() == null || intentoFallido.getUsername().trim().isEmpty()) {
			return;
		}
		usuarioService.marcarIntentoError(intentoFallido.getUsername());
	}

}
