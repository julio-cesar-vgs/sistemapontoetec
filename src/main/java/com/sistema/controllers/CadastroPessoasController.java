package com.sistema.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sistema.models.CadastroPessoas;
import com.sistema.models.Departamento;
import com.sistema.models.Funcao;
import com.sistema.models.RegistroPontos;
import com.sistema.repository.CadastroPessoasRepository;
import com.sistema.repository.DepartamentoRepository;
import com.sistema.repository.FuncaoRepository;
import com.sistema.repository.RegistroPontosRepository;

@Controller
@ComponentScan()
public class CadastroPessoasController {

	@Autowired
	private CadastroPessoasRepository cp;
	
	@Autowired
	private RegistroPontosRepository rp;
	
	@Autowired
	private DepartamentoRepository dr;
	
	@Autowired
	private FuncaoRepository fr;
	
	@RequestMapping(value="/cadastroPessoas", method=RequestMethod.GET)
	public ModelAndView listaPessoasNoCadastro() {
		ModelAndView mv = new ModelAndView("views/formCadastroPessoas");
		Iterable<CadastroPessoas> registro = cp.findAll();
		mv.addObject("registro", registro);
		
		Iterable<Departamento> departamento = dr.findAll();
		mv.addObject("dep", departamento);
		Iterable<Funcao> funcao = fr.findAll();
		mv.addObject("func", funcao);
		return mv;
	}
 	
	@PostMapping(value="/cadastroPessoas", params = "action=inserirPessoa")
	public String listaPessoasNoCadastro(CadastroPessoas cadastro, BindingResult bindingResult) {
		cp.save(cadastro);
		return "redirect:/pessoas";
	}
	
	@RequestMapping("/pessoas")
	public ModelAndView listaPessoas() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<CadastroPessoas> consulta = cp.findAll();
		mv.addObject("consulta", consulta);
		List<Departamento> departamento = (List<Departamento>) dr.findAll();
		mv.addObject("departamento", departamento);
		Iterable<Funcao> funcao = fr.findAll();
		mv.addObject("funcao", funcao);

		return mv;
	}
	
	@RequestMapping(value="/registrarponto/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesPontos(@PathVariable(value="codigo") int codigo) {
		
		//consulta dos dados da pessoa que irá registrar o ponto
		
		//declaração da variável iddapessoapesquisada que irá receber o código de registro da pessoa envolvida
		CadastroPessoas iddapessoapesquisada = cp.findByCodigo(codigo);
		
		//set do objeto ModelAndView através da variável mv, pois, ela que irá ativar e receber os dados do formulário
		ModelAndView mv = new ModelAndView("views/formRegistroPontos");
		mv.addObject("registro",iddapessoapesquisada);

		Iterable<RegistroPontos> pontos = rp.findByPessoa(codigo);
		mv.addObject("pontos", pontos);
		
		return mv;
	}
	
	//insere registros dentro da tabela
	@RequestMapping(value="/registrarponto/{codigo}", method=RequestMethod.POST)
	public String detalhesRegistroPontos(@PathVariable("codigo") int codigo) {
		CadastroPessoas pessoa = cp.findByCodigo(codigo);
		RegistroPontos pontos;
		int idRegistroPontos = rp.ConsultaUltimoRegistro(codigo);
		if (idRegistroPontos==0) {
			pontos = new RegistroPontos();
			pontos.setPessoa(pessoa);
			pontos.setDtiniciojornada(LocalDateTime.now());
		} else {
			pontos = rp.findById(idRegistroPontos);
			pontos.setPessoa(pessoa);
			if (pontos.getDtiniciojornada()!=null &&
					pontos.getDtiniciopausa()!=null&&
					pontos.getDtterminopausa()!=null&&
					pontos.getDtterminojornada()!=null) {
				pontos = new RegistroPontos();
				pontos.setPessoa(pessoa);
				pontos.setDtiniciojornada(LocalDateTime.now());
			} else if(pontos.getDtterminopausa()!=null) {
				pontos.setDtterminojornada(LocalDateTime.now());
			} else if(pontos.getDtiniciopausa()!= null) {
				pontos.setDtterminopausa(LocalDateTime.now());
			} else if(pontos.getDtiniciojornada() != null) {
				pontos.setDtiniciopausa(LocalDateTime.now());
			}
		};
		rp.save(pontos);
		return "redirect:/registrarponto/{codigo}";
	}

	@RequestMapping(value="/cadastroPessoas/edit/{codigo}", method=RequestMethod.GET)
	public ModelAndView editarCadastroPessoas(@PathVariable(value="codigo") int codigo) {
		
		//consulta dos dados da pessoa que irá registrar o ponto
		
		//declaração da variável iddapessoapesquisada que irá receber o código de registro da pessoa envolvida
		CadastroPessoas iddapessoapesquisada = cp.findByCodigo(codigo);
		
		//set do objeto ModelAndView através da variável mv, pois, ela que irá ativar e receber os dados do formulário
		ModelAndView mv = new ModelAndView("views/formCadastroPessoasEditar");
		mv.addObject("registro",iddapessoapesquisada);

		Iterable<Departamento> departamento = dr.findAll();
		mv.addObject("dep", departamento);
		Iterable<Funcao> funcao = fr.findAll();
		mv.addObject("func", funcao);
		return mv;
	}

	@RequestMapping("/deletar")
	public String deletarPessoa(int codigo) {
		CadastroPessoas pessoas = cp.findByCodigo(codigo);
		cp.delete(pessoas);
		return "redirect:/pessoas";
	}
	
	@RequestMapping("/deletarponto")
	public String deletarRegistro(int codigo) {
		RegistroPontos pontos = rp.findById(codigo);
		rp.delete(pontos);
		return "redirect:/pessoas";
	}
}
