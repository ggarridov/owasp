/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a5perdidacontrol.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.tutorial.owasp.a5perdidacontrol.modelo.dao.UsuarioDao;
import cl.tutorial.owasp.a5perdidacontrol.modelo.entidad.Usuario;

/**
 * @author gerardo
 *
 */
@Controller
public class MainControlador {

	@Autowired
	private UsuarioDao usuarioDao;

	@GetMapping({ "/", "/index.html", "/seguro/index", "/seguro/index.html" })
	public String inicio(final Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority())
				.collect(Collectors.toList());
		model.addAttribute("roles", roles.toArray(new String[roles.size()]));
		if (authentication.getPrincipal() instanceof UserDetails) {
			model.addAttribute("usuario", ((UserDetails) authentication.getPrincipal()).getUsername());
		} else {
			model.addAttribute("usuario", authentication.getPrincipal().toString());
		}
		model.addAttribute("usuarios", usuarioDao.findAll());
		return "seguro/index";
	}

	@GetMapping("/seguro/editar/{id}")
	@PreAuthorize("hasPermission(#id, 'read')")
	public String irActualizar(@PathVariable("id") final long id, final Model model) {
		Optional<Usuario> usuario = usuarioDao.findById(id);
		if (!usuario.isPresent()) {
			return inicio(model);
		}
		model.addAttribute("usuario", usuario.get());
		return "seguro/editar";
	}

	@GetMapping({ "/login", "/login.html" })
	public String login(final Model model) {
		return "login";
	}
}
