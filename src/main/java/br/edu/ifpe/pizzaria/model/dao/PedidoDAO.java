package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.ifpe.pizzaria.model.domain.Pedido;
import br.edu.ifpe.pizzaria.model.domain.PedidoPizza;
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
	
	public void salvar(Pedido pedido, List<PedidoPizza> pedidosPizza){
		

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(pedido);
			
			for(int pos =0; pos < pedidosPizza.size(); pos++){
				PedidoPizza pedidoPizza = pedidosPizza.get(pos);
				pedidoPizza.setPedido(pedido);
				
				sessao.save(pedidoPizza);
			}
			transacao.commit();

		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close(); // testar 
		}
		
	}

}
