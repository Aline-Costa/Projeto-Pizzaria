package br.edu.ifpe.pizzaria.model.dao;

public interface DAO <T>{

	public void salvar(T t);
	
	public void excluir(T t);
	
	public void editar(T t);
	
	public void merge(T t);
	
}
