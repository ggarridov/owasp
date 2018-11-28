/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.seguridad;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import cl.tutorial.owasp.a2autenticacion.segura.negocio.ILoginService;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.IUsuarioService;
import cl.tutorial.owasp.a2autenticacion.segura.util.UtilEstatico;

/**
 * <p>
 * Clase que se activa cuando la autenticacion es exitosa
 * </p>
 *
 * @author gerardo
 *
 */
public class ManipuladorAutenticacionExitosa implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private ILoginService loginService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private HttpServletRequest request;

	public static final Logger LOGGER = LoggerFactory.getLogger(ManipuladorAutenticacionExitosa.class);

	@Override
	public void onApplicationEvent(final AuthenticationSuccessEvent event) {
		String ip = UtilEstatico.getIpRequest(request);
		String username = "";
		LOGGER.info("Usuario {} conectado desde ip {}", username, ip);
		if (event.getAuthentication().getPrincipal() instanceof UserDetails) {
			username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		} else {
			username = event.getAuthentication().getPrincipal().toString();
		}
		loginService.loginExisto(username, ip);
		usuarioService.reiniciarIntentoError(username, ip);
	}

}
