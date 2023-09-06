package br.com.fiap.spring.mvc.dao.impl;

import java.lang.reflect.ParameterizedType;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.fiap.spring.mvc.dao.GenericDAO;

/*
 * A notação "@PersistenceContext" no SpringMVC serve para o Spring automaticamente
 * colocar um objeto/instância do entityManager na variável.
 */

public class GenericDAOImpl<T, K> implements GenericDAO<T, K> {
	
	@PersistenceContext
	protected EntityManager em;

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public void cadastrar(T entidade) {
		em.persist(entidade);
	}
	public void atualizar(T entidade) {
		em.merge(entidade);
	}

	public T buscar(K chave) {
		return (em.find(clazz, chave));
	}
	
	public void remover(K chave) throws Exception {
		T entidade = buscar(chave);
		if(entidade == null) {
			throw (new Exception("Entidade não encontrada"));
		}
		em.remove(entidade);
	}
	
	public List<T> listar(){
		return (em.createQuery("from      "+ clazz.getName(),clazz).getResultList());
	}
}
