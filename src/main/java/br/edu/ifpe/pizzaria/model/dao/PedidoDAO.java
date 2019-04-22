package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ifpe.pizzaria.model.domain.Pedido;
import br.edu.ifpe.pizzaria.util.HibernateUtil;


public class PedidoDAO  extends GenericDAO{
	
	public Pedido buscarPorCodigo(Long codPedido) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Pedido pedido = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Pedido.buscarPorCodigo");
			consulta.setLong("codPedido", codPedido);
			
			pedido = (Pedido) consulta.uniqueResult();
			
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return pedido;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pedido.class);
			List<Pedido> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

}
