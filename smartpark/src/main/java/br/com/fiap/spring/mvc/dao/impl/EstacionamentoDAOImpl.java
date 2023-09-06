package br.com.fiap.spring.mvc.dao.impl;

import br.com.fiap.spring.mvc.entity.Estacionamento;
import org.springframework.stereotype.Repository;

import br.com.fiap.spring.mvc.dao.EstacionamentoDAO;

/*
 * A notação "@Repository" no SpringMVC significa que esta classe
 * é responsável armazenar/recueprar informações do banco de dados ou web service.
 */

@Repository
public class EstacionamentoDAOImpl extends GenericDAOImpl<Estacionamento,Integer> implements EstacionamentoDAO  {
	
}
