package br.edu.ifpe.pizzaria.model;

import java.util.List;

import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.dao.PizzaDAO;
import br.edu.ifpe.pizzaria.model.domain.Pizza;

public class PizzaModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarPizza(Pizza p) {
		try {
			if (p != null) {
				dao.merge(p);
				Messages.addGlobalInfo("Pizza salva com sucesso!");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar a pizza!");
			erro.printStackTrace();
		}
	}

	public void removerPizza(Pizza p) {
		try {
			if (p != null) {
				dao.excluir(p);
				Messages.addGlobalInfo("Pizza excluída com sucesso!");

			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir a pizza!");
			erro.printStackTrace();
		}
	}

	public void atualizarPizza(Pizza p) {
		try {
			if (p != null) {
				dao.editar(p);
				Messages.addFlashGlobalError("Pizza atualizada com sucesso!.");
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar a pizza!");
			erro.printStackTrace();
		}

	}

	public List<Pizza> listarPizza() {

		PizzaDAO pizzaDAO = new PizzaDAO();
		List<Pizza> resultado = pizzaDAO.listar();

		return resultado;
	}

}