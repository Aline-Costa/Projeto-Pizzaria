package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.edu.ifpe.pizzaria.model.ClienteModel;
import br.edu.ifpe.pizzaria.model.domain.Cliente;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@PostConstruct
	public void listar() {

		ClienteModel clienteModel = new ClienteModel();
		clientes = clienteModel.listarcliente();

	}

	public void novo() {

		cliente = new Cliente();

	}

	public void salvar() {

		ClienteModel clienteModel = new ClienteModel();

		clienteModel.registrarCliente(cliente);

		novo();
		clientes = clienteModel.listarcliente();

	}

	public void excluir(ActionEvent evento) {

		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

		ClienteModel clienteModel = new ClienteModel();
		clienteModel.removerCliente(cliente);
		clientes = clienteModel.listarcliente();

	}

	public void editar(ActionEvent evento) {

		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

		ClienteModel clienteModel = new ClienteModel();
		clienteModel.atualizarCliente(cliente);
		clientes = clienteModel.listarcliente();

	}

}
