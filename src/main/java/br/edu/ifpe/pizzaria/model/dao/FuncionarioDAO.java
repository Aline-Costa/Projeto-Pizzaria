package br.edu.ifpe.pizzaria.model.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpe.pizzaria.model.domain.Funcionario;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO{
	
	public Funcionario buscarPorCodigo(Long codFuncionario) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Funcionario funcionario = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codFuncionario", codFuncionario);
			
			funcionario = (Funcionario) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return funcionario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			List<Funcionario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
}