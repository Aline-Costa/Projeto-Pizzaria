package br.edu.ifpe.pizzaria.model;

import java.util.List;

import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;

public class ClienteModel {

	DAO<Object> dao = new GenericDAO();
	private List<Cliente> clientes;

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void registrarCliente(Cliente c) {
		try {
			if (validaEmail(c.getEmail()) && c != null) {
				Messages.addFlashGlobalError("E-mail digitado já está cadastrado no sistema!");
			} else {
				dao.merge(c);
				Messages.addGlobalInfo("Cadastro realizado com sucesso! Faça seu login!");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar o cliente!");
			erro.printStackTrace();
		}
	}

	public void removerCliente(Cliente c) {
		try {
			if (c != null) {
				dao.excluir(c);
				Messages.addGlobalInfo("Cliente excluído com sucesso!");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o cliente!");
			erro.printStackTrace();
		}
	}

	public void atualizarCliente(Cliente c) {
		try {
			if (c != null) {
				dao.editar(c);
				Messages.addFlashGlobalError("Usuário atualizado com sucesso!.");
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o cliente!");
			erro.printStackTrace();
		}
	}

	public List<Cliente> listarcliente() {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();

		return resultado;
	}

	public boolean validaEmail(String email) {

		ClienteDAO clienteDAO = new ClienteDAO();
		clientes = clienteDAO.listar();

		for (int i = 0; i < clientes.size(); i++) {

			if (clientes.get(i).getEmail().equalsIgnoreCase(email))
				return true;
		}
		return false;
	}
}
