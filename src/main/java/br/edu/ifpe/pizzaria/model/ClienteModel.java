package br.edu.ifpe.pizzaria.model;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;

public class ClienteModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarCliente(Cliente c) {
		try {
			if (c != null) {
				dao.salvar(c);
				System.out.println("Usuário salvo com sucesso!.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar usuário." + e);
		}
	}

	public void removerCliente(Cliente c) {
		try {
			if (c != null) {
				dao.excluir(c);
				System.out.println("Usuário excluído com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover usuário." + e);
		}
	}

	public void atualizarCliente(Cliente c) {
		try {
			if (c != null) {
				dao.editar(c);
				System.out.println("Usuário atualizado com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar usuário." + e);
		}
	}
}
