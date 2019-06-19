package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.edu.ifpe.pizzaria.model.domain.Menu;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class MenuDAO {
	
	@SuppressWarnings("unchecked")
	public List<Menu> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Menu.class);
			List<Menu> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

}
