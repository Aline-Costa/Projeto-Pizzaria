package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ifpe.pizzaria.model.domain.Telefone;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class TelefoneDAO {
	
	public Telefone buscarPorCodigo(Long codTelefone) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Telefone telefone = null;

		try {

			Query consulta = sessao.getNamedQuery("Telfone.buscarPorCodigo");
			consulta.setLong("codTelefone", codTelefone);

			telefone = (Telefone) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return telefone;
	}
	
	@SuppressWarnings("unchecked")
	public List<Telefone> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Telefone.class);
			List<Telefone> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
	

}
