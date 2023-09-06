package br.com.fiap.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.spring.mvc.dao.EstacionamentoDAO;
import br.com.fiap.spring.mvc.entity.Estacionamento;

/*
 * A notação "@Autowired" serve para SpringMVC colocar automaticamente
 * um objeto do tipo "EstacionamentoDAO dao" na variável.
 * 
 * A notação "@Transactional" serve para o SpringMVC habilitar automaticamente
 * as transações com o banco exemplo: commit.
 * 
 * A notação "@RequestMapping" habilita  uma  rota  com  caminho  específico 
 * (Estacionamento,nesse caso) para todas as sub-rotas dentro da classe, ou seja, todas as 
 * sub-rotas deverão conter um /Estacionamento/ na URL
 * 
 * A notação "@GetMapping" habilita uma rota do verbo GET,que nada mais é do que 
 * obter a listagem de todos os elementos de um repository.
 * 
 * A notação "@PostMapping" habilita uma rota do verbo POSTpara inserir um elemento.
 * 
 * O tipo "ModelAndView" é uma classe especial, 
 * que pode retornar tanto a View(página) quanto o Model(informações).
 * 
 * É recomendado sempre redirecionar a página após um Post, caso usuário
 * aperte um F5 ele acaba se cadastrando 2x. Obs: O redirect deve ser feito na ação
 * e não na página jsp.
 * 
 */

@Controller
@RequestMapping("estacionamento")
public class EstacionamentoController {

	@Autowired
	EstacionamentoDAO dao;

	@GetMapping("cadastrar")
	public String abrirForm(Estacionamento estacionamento) {
		return ("estacionamento/cadastro");
	}

	@Transactional
	@PostMapping("cadastrar")
	public ModelAndView processarForm(Estacionamento estacionamento, RedirectAttributes attr) {
		try {
			dao.cadastrar(estacionamento);
		} catch (Exception e) {
			e.printStackTrace();
			return (new ModelAndView("estacionamento/cadastro"));
		}
		attr.addFlashAttribute("msg", "Cadastrado com sucesso!");
		return (new ModelAndView("redirect:/estacionamento/cadastrar"));	
	}

	@GetMapping("listar")
	public ModelAndView listar() {
		return (new ModelAndView("estacionamento/lista").addObject("lista", dao.listar()));
	}

	@GetMapping("editar/{id}")
	public ModelAndView abrirFormEdicao(@PathVariable("id") int codigo) {
		return (new ModelAndView("estacionamento/edicao").addObject("estacionamento", dao.buscar(codigo)));
	}

	@Transactional
	@PostMapping("editar")
	public ModelAndView processarFormEdicao(Estacionamento estacionamento, RedirectAttributes attr) {
		
		try {
			dao.atualizar(estacionamento);
		} catch (Exception e) {
			e.printStackTrace();
			return (new ModelAndView("redirect:/estacionamento/edicao")
					.addObject("msg", "Falha ao atualizar!"));
		}
		attr.addFlashAttribute("msg", "Atualizado com sucesso!");
		return (new ModelAndView("redirect:/estacionamento/listar"));
	}
	
	@Transactional
	@PostMapping("excluir")
	public ModelAndView excluir(int codigo, RedirectAttributes attr) {
		try {	
			dao.remover(codigo);	
		} catch (Exception e) {
			e.printStackTrace();
			return (new ModelAndView("estacionamento/lista").addObject("msg", "Erro ao excluir!"));
		}
		attr.addFlashAttribute("msg", "Excluido com sucesso!");
		return (new ModelAndView("redirect:/estacionamento/listar"));
		
	}
}
