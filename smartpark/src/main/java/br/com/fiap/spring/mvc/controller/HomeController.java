package br.com.fiap.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 *  A notação "@controller" no SpringMVC significa que está classe
 *  é responsável por receber requisições e enviar as informações.
 */

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return ("/home/index");
	}
	
	@GetMapping("contato")
	public String contanto() {
		return ("/home/contato");
	}
}
