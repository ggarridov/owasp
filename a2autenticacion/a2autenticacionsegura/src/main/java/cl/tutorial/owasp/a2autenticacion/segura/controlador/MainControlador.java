/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.RolesEnum;

/**
 * @author gerardo
 *
 */
@Controller
public class MainControlador {

	@GetMapping({ "/", "/index.html", "/seguro/index", "/seguro/index.html" })
	public String inicio(final Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<RolesEnum> roles = authentication.getAuthorities().stream().map(r -> RolesEnum.valueOf(r.getAuthority()))
				.collect(Collectors.toList());
		model.addAttribute("roles", roles.toArray(new RolesEnum[roles.size()]));
		if (authentication.getPrincipal() instanceof UserDetails) {
			model.addAttribute("usuario", ((UserDetails) authentication.getPrincipal()).getUsername());
		} else {
			model.addAttribute("usuario", authentication.getPrincipal().toString());
		}
		return "seguro/index";
	}

	@GetMapping({ "/login", "/login.html" })
	public String login(final Model model) {
		return "login";
	}
}
