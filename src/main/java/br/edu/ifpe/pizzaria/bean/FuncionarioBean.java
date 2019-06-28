package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.FuncionarioDAO;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Usuario> usuarios;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {

			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			funcionarios = funcionarioDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		funcionario = new Funcionario();
	}

	public void salvar() {
		try {
			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			if (validaEmail(funcionario.getEmail())) {
				Messages.addFlashGlobalError("E-mail digitado já está cadastrado no sistema!");
			} else {

				funcionarioDao.merge(funcionario);

				novo();
				funcionarios = funcionarioDao.listar();
				Messages.addGlobalInfo("Funcionário salvo com sucesso!");
			}
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar o funcionário!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			funcionarioDao.excluir(funcionario);
			funcionarios = funcionarioDao.listar();
			Messages.addGlobalInfo("funcionário excluído com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o funcionário!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

			FuncionarioDAO funcionarioDao = new FuncionarioDAO();
			funcionarioDao.editar(funcionario);
			funcionarios = funcionarioDao.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o funcionário!");
			erro.printStackTrace();
		}

	}

	public void retornaUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar();

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