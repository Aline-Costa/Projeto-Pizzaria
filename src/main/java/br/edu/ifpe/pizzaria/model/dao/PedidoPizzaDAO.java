package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.edu.ifpe.pizzaria.model.domain.PedidoPizza;
import br.edu.ifpe.pizzaria.util.HibernateUtil;


public class PedidoPizzaDAO {
	
	@SuppressWarnings("unchecked")
	public List<PedidoPizza> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PedidoPizza.class);
			List<PedidoPizza> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

}
