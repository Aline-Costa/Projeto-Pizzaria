package br.edu.ifpe.pizzaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.FuncionarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Cliente cliente;
	private Cliente clienteLogado;
	private Funcionario funcionario;
	private Funcionario funcionarioLogado;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	
	public void iniciar() {
		cliente = new Cliente();
		funcionario = new Funcionario();
	}

	public void autenticarCliente() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteLogado = clienteDAO.autenticar(cliente.getEmail(), cliente.getSenha());
			if(clienteLogado == null){
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/pedido.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public void autenticarFuncionario() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionario.getEmail(), funcionario.getSenha());
			if(funcionarioLogado == null){
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
}
