package br.edu.ifpe.pizzaria.model;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;

public class FuncionarioModel {

	DAO<Object> dao = new GenericDAO();

	public void registrarFuncionario(Funcionario f) {
		try {
			if (f != null) {
				dao.salvar(f);
				System.out.println("Funcionário salvo com sucesso!.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar usuário." + e);
		}
	}

	public void removerFuncionario(Funcionario f) {
		try {
			if (f != null) {
				dao.excluir(f);
				System.out.println("Usuário excluído com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover usuário." + e);
		}
	}

	public void atualizarFuncionario(Funcionario f) {
		try {
			if (f != null) {
				dao.editar(f);
				System.out.println("Usuário atualizado com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar usuário." + e);
		}
	}

}
