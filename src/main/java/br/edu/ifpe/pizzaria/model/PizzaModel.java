package br.edu.ifpe.pizzaria.model;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Pizza;

public class PizzaModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarPizza(Pizza p) {
		try {
			if (p != null) {
				dao.salvar(p);
				System.out.println("Pizza salva com sucesso!.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar pizza." + e);
		}
	}

	public void removerPizza(Pizza p) {
		try {
			if (p != null) {
				dao.excluir(p);
				System.out.println("Pizza excluída com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover pizza." + e);
		}
	}

	public void atualizarPizza(Pizza p) {
		try {
			if (p != null) {
				dao.editar(p);
				System.out.println("Pizza atualizada com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar pizza." + e);
		}
	}

}