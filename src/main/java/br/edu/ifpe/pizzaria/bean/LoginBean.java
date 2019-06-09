package br.edu.ifpe.pizzaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Cliente cliente;
	private Cliente clienteLogado;

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
	
	@PostConstruct
	public void iniciar() {
		cliente = new Cliente();
	}

	public void autenticar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteLogado = clienteDAO.autenticar(cliente.getEmail(), cliente.getSenha());
			if(clienteLogado == null){
				Messages.addGlobalError("Email e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
}
