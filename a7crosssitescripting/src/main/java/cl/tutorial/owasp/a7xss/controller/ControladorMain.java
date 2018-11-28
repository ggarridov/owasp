/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a7xss.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cl.tutorial.owasp.a7xss.controller.dto.ObjetoDto;

/**
 * @author gerardo
 *
 */
@Controller
public class ControladorMain {

	private static String xssMensaje;

	@PostMapping("/guardar")
	public String editarPost(@Valid final ObjetoDto objetoDto, final BindingResult result, final Model model) {
		model.addAttribute("objetoDto", objetoDto);
		xssMensaje = objetoDto.getValor();
		model.addAttribute("xssMensaje", xssMensaje);
		return "editar";
	}

	@GetMapping({ "/", "/editar" })
	public String editarGet(final Model model) {
		ObjetoDto o = new ObjetoDto();
		model.addAttribute("objetoDto", o);
		if (xssMensaje == null) {
			xssMensaje = o.getValor();
		}
		model.addAttribute("xssMensaje", xssMensaje);
		return "editar";
	}

}
