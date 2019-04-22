package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ifpe.pizzaria.model.domain.Pizza;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class PizzaDAO extends GenericDAO{
	
	public Pizza buscarPorCodigo(Long codPizza) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Pizza pizza = null;

		try {

			Query consulta = sessao.getNamedQuery("Pizza.buscarPorCodigo");
			consulta.setLong("codPizza", codPizza);

			pizza = (Pizza) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return pizza;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pizza> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pizza.class);
			List<Pizza> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
}