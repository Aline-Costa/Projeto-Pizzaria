package br.edu.ifpe.pizzaria.model;

import java.util.List;

import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.FuncionarioDAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;

public class FuncionarioModel {

	private List<Funcionario> funcionarios;

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	DAO<Object> dao = new GenericDAO();

	public void registrarFuncionario(Funcionario f) {
		try {
			if (validaEmail(f.getEmail()) && f != null) {
				Messages.addFlashGlobalError("E-mail digitado já está cadastrado no sistema!");
			} else {
				dao.merge(f);
				Messages.addGlobalInfo("Funcionário salvo com sucesso!");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar o funcionário!");
			erro.printStackTrace();
		}
	}

	public void removerFuncionario(Funcionario f) {
		try {
			if (f != null) {
				dao.excluir(f);
				Messages.addGlobalInfo("funcionário excluído com sucesso!");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o funcionário!");
			erro.printStackTrace();
		}
	}

	public void atualizarFuncionario(Funcionario f) {
		try {
			if (f != null) {
				dao.editar(f);
				Messages.addFlashGlobalError("Usuário atualizado com sucesso!.");
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o funcionário!");
			erro.printStackTrace();
		}
	}

	
	public List<Funcionario> listarFuncionario() {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();
		
		return resultado;

	}

	public boolean validaEmail(String email) {

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarios = funcionarioDAO.listar();

		for (int i = 0; i < funcionarios.size(); i++) {

			if (funcionarios.get(i).getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}

}
