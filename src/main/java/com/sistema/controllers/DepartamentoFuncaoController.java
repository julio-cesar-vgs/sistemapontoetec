package com.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.models.Departamento;
import com.sistema.models.Funcao;
import com.sistema.repository.DepartamentoRepository;
import com.sistema.repository.FuncaoRepository;

@Controller
@ComponentScan()
public class DepartamentoFuncaoController {

	@Autowired
	private DepartamentoRepository dp;
	@Autowired
	private FuncaoRepository fr;

	@RequestMapping(value = "/departamentofuncao", method = RequestMethod.GET)
	public ModelAndView listaDepartamentoFuncao() {
		ModelAndView mv = new ModelAndView("views/formCadastroDepartamentoFuncao");
		Iterable<Departamento> departamento = dp.findAll();
		mv.addObject("departamento", departamento);
		Iterable<Funcao> funcao = fr.findAll();
		mv.addObject("funcao", funcao);
		return mv;
	}

	@RequestMapping(value = "/departamentofuncao", method = RequestMethod.POST, params = "action=departamento")
	public String formSalvarDepartamento(Departamento departamento) {
		dp.save(departamento);
		return "redirect:/departamentofuncao";
	}

	@RequestMapping(value = "/departamentofuncao", method = RequestMethod.POST, params = "action=funcao")
	public String formSalvarFuncao(Funcao funcao) {
		fr.save(funcao);
		return "redirect:/departamentofuncao";
	}

}
