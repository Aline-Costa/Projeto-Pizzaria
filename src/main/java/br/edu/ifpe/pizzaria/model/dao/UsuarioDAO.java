package br.edu.ifpe.pizzaria.model.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import br.edu.ifpe.pizzaria.model.domain.Usuario;
import br.edu.ifpe.pizzaria.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO{
	
	public Usuario buscarPorCodigo(Long codUsuario) {

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Usuario usuario = null;

		try {

			Query consulta = sessao.getNamedQuery("Usuario.buscarPorCodigo");
			consulta.setLong("codUsuario", codUsuario);

			usuario = (Usuario) consulta.uniqueResult();

		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			List<Usuario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
		
}
