/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.seguridad;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cl.tutorial.owasp.a5perdidacontrol.modelo.dto.UsuarioApp;

/**
 * @author gerardo
 *
 */
@Component
public class UsuarioOwnerSecure implements PermissionEvaluator {

	@Override
	public boolean hasPermission(final Authentication auth, final Object targetDomainObject, final Object permission) {
		if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
			return false;
		}
		return hasPrivilege(auth, targetDomainObject);
	}

	@Override
	public boolean hasPermission(final Authentication auth, final Serializable targetId, final String targetType,
			final Object permission) {
		// Serializar no permitido
		return false;
	}

	private boolean hasPrivilege(final Authentication auth, final Object usuarioID) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		long userId;
		if (authentication.getPrincipal() instanceof UsuarioApp) {
			userId = ((UsuarioApp) authentication.getPrincipal()).getId();
		} else {
			return false;
		}
		return userId == (Long) usuarioID;
	}
}