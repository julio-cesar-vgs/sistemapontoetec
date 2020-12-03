package com.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.models.CadastroPessoas;
import com.sistema.models.EnvioMensagens;
import com.sistema.repository.CadastroPessoasRepository;
import com.sistema.repository.EnvioMensagensRepository;

@Controller
@ComponentScan
public class EnvioMensagensController {
	@Autowired
	private CadastroPessoasRepository cp;
	
	@Autowired
	private EnvioMensagensRepository em;
	
	@RequestMapping("/enviomensagens")
	public ModelAndView carregaMensagens() {
		
		ModelAndView mv = new ModelAndView("views/formEnviaMensagem");
		Iterable<CadastroPessoas> pessoas = cp.findAll();
				
		mv.addObject("registroPessoas", pessoas);
		return mv;
	}
	
	@PostMapping(value="/enviomensagens", params="action=enviodemensagens")
	public String enviarMensagem(EnvioMensagens envio, BindingResult bindingResult) {
		em.save(envio);
		return "redirect:/enviomensagens";
	}
}
