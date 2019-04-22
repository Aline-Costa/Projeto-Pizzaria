package br.edu.ifpe.pizzaria.model;

import java.util.List;

import br.edu.ifpe.pizzaria.model.dao.BebidaDAO;
import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Bebida;


public class BebidaModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarBebida(Bebida b) {
		try {
			if (b != null) {
				dao.salvar(b);
				System.out.println("Bebida salva com sucesso!.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar bebida." + e);
		}
	}

	public void removerBebida(Bebida b) {

		BebidaDAO bebidaDAO = new BebidaDAO();
		b = bebidaDAO.buscarPorCodigo(b.getCodBebida());

		try {
			if (b != null) {
				dao.excluir(b);
				System.out.println("Bebida excluída com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover bebida." + e);
		}
	}

	public void atualizarBebida(Bebida b) {

		BebidaDAO bebidaDAO = new BebidaDAO();
		b = bebidaDAO.buscarPorCodigo(b.getCodBebida());

		try {
			if (b != null) {
				dao.editar(b);
				System.out.println("Bebida atualizada com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar bebida." + e);
		}
	}

	public void buscarBebida(Bebida b) {

		BebidaDAO bebidaDAO = new BebidaDAO();
		try {

			if (b != null) {
				bebidaDAO.buscarPorCodigo(b.getCodBebida());
				System.out.println(b);
			}
		} catch (Exception e) {
			System.out.println("Usuário não encontrado!" + e);
		}
	}
	
	public void listarBebida() {
		BebidaDAO bebidaDAO = new BebidaDAO();
		List<Bebida> resultado = bebidaDAO.listar();

		for (Bebida bebida : resultado) {
			System.out.println(bebida);
		}
	}

}
