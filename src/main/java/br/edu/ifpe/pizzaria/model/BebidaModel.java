package br.edu.ifpe.pizzaria.model;

import java.util.List;

import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.BebidaDAO;
import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Bebida;

public class BebidaModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarBebida(Bebida b) {
		BebidaDAO bebidaDAO = new BebidaDAO();
		try {
			if (b != null) {
				bebidaDAO.merge(b);
				Messages.addGlobalInfo("Bebida salva com sucesso!");
			}

		} catch (Exception erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar a bebida!");
			erro.printStackTrace();
		}
	}

	public void removerBebida(Bebida b) {

		BebidaDAO bebidaDAO = new BebidaDAO();
		b = bebidaDAO.buscarPorCodigo(b.getCodBebida());

		try {
			if (b != null) {
				dao.excluir(b);
				Messages.addGlobalInfo("Bebida excluída com sucesso!");
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir a bebida!");
			erro.printStackTrace();
		}
	}

	public void atualizarBebida(Bebida b) {

		BebidaDAO bebidaDAO = new BebidaDAO();
		b = bebidaDAO.buscarPorCodigo(b.getCodBebida());

		try {
			if (b != null) {
				dao.editar(b);
				Messages.addFlashGlobalError("Bebida atualizada com sucesso!.");
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar a bebida!");
			erro.printStackTrace();
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

	public List<Bebida> listarBebida() {
		
		BebidaDAO bebidaDAO = new BebidaDAO();
		List<Bebida> resultado = bebidaDAO.listar();

		return resultado;
	}
}
