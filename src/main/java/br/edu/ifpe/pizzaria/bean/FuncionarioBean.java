package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.FuncionarioModel;
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

			FuncionarioModel funcionarioModel = new FuncionarioModel();
			funcionarios = funcionarioModel.listarFuncionario();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionários.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		funcionario = new Funcionario();
	}

	public void salvar() {

		FuncionarioModel funcionarioModel = new FuncionarioModel();

		funcionarioModel.registrarFuncionario(funcionario);
		novo();
		funcionarios = funcionarioModel.listarFuncionario();

	}

	public void excluir(ActionEvent evento) {

		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

		FuncionarioModel funcionarioModel = new FuncionarioModel();
		funcionarioModel.removerFuncionario(funcionario);
		funcionarios = funcionarioModel.listarFuncionario();

	}

	public void editar(ActionEvent evento) {

		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

		FuncionarioModel funcionarioModel = new FuncionarioModel();
		funcionarioModel.atualizarFuncionario(funcionario);
		funcionarios = funcionarioModel.listarFuncionario();

	}

	public void retornaUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar();

	}
}