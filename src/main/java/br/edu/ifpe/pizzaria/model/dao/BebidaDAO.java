package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ifpe.pizzaria.model.domain.Bebida;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class BebidaDAO extends GenericDAO{
	
	public Bebida buscarPorCodigo(Long codBebida) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Bebida bebida = null;

		try {

			Query consulta = sessao.getNamedQuery("Bebida.buscarPorCodigo");
			consulta.setLong("codBebida", codBebida);

			bebida = (Bebida) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return bebida;
	}
	
	@SuppressWarnings("unchecked")
	public List<Bebida> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Bebida.class);
			List<Bebida> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

}
