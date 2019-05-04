package br.edu.ifpe.pizzaria.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpe.pizzaria.model.dao.DAO;
import br.edu.ifpe.pizzaria.model.dao.GenericDAO;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

public class UsuarioModel {

	DAO<Object> dao = new GenericDAO();
	private ArrayList<Usuario> usuarios = new ArrayList<>();

	public void registrarUsuario(Usuario u) {
		try {
			if (u.getBairro() != null && u.getEmail() != null && u.getLogin() != null && u.getNome() != null
					&& u.getNumCasa() != null && u.getRua() != null && u.getSenha() != null
					&& !this.existeLogin(u.getLogin()) && !this.existeEmail(u.getEmail())) {
				dao.salvar(u);
				System.out.println("Usuário salvo com sucesso!.");
			}

		} catch (Exception e) {
			System.out.println("Erro ao salvar usuário." + e);
		}
	}

	private boolean existeLogin(String login) {
		boolean ret = false;
		for (Usuario u : usuarios) {
			if (u.getLogin() == login) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	private boolean existeEmail(String email) {
		boolean ret = false;
		for (Usuario u : usuarios) {
			if (u.getEmail() == email) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public void removerUsuario(Usuario u) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		u = usuarioDAO.buscarPorCodigo(u.getCodUsuario());

		try {
			if (u != null) {
				dao.excluir(u);
				System.out.println("Usuário excluído com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao remover usuário." + e);
		}
	}

	public void atualizarUsuario(Usuario u) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		u = usuarioDAO.buscarPorCodigo(u.getCodUsuario());

		try {
			if (u != null) {
				dao.editar(u);
				System.out.println("Usuário atualizado com sucesso!.");
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar usuário." + e);
		}
	}

	public void buscarUsuario(Usuario u) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {

			if (u != null) {
				usuarioDAO.buscarPorCodigo(u.getCodUsuario());
				System.out.println(u);
			}
		} catch (Exception e) {
			System.out.println("Usuário não encontrado!" + e);
		}
	}

	public void listarUsuario() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		for (Usuario usuario : resultado) {
			System.out.println(usuario);
		}
	}

}
