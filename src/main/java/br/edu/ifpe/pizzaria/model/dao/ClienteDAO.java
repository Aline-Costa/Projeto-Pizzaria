package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class ClienteDAO extends GenericDAO{
	
	public Cliente buscarPorCodigo(Long codCliente) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Cliente cliente = null;

		try {

			Query consulta = sessao.getNamedQuery("Cliente.buscarPorCodigo");
			consulta.setLong("codCliente", codCliente);

			cliente = (Cliente) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return cliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cliente.class);
			List<Cliente> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public Cliente autenticar(String email, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{
			Criteria consulta = sessao.createCriteria(Cliente.class);
			
			consulta.add(Restrictions.eq("email", email));
			
			consulta.add(Restrictions.eq("senha", senha));
			
			/*
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			*/
			
			
			Cliente resultado = (Cliente) consulta.uniqueResult();
			
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
}