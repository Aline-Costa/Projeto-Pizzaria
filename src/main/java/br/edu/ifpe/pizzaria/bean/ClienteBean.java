package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	private Cliente cliente;
	private List<Cliente> clientes;
	private List<Usuario> usuarios;
	private List<Usuario> usuarios2;
	private Usuario usuario;
	private Usuario usuarioLog;

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuarioLog() {
		return usuarioLog;
	}

	public void setUsuarioLog(Usuario usuarioLog) {
		this.usuarioLog = usuarioLog;
	}

	public List<Usuario> getUsuarios2() {
		return usuarios2;
	}

	public void setUsuarios2(List<Usuario> usuarios2) {
		this.usuarios2 = usuarios2;
	}

	@PostConstruct
	public void listar() {
		try {

			ClienteDAO clienteDao = new ClienteDAO();
			clientes = clienteDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		cliente = new Cliente();

	}

	public void salvar() {
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.merge(cliente);

			novo();
			clientes = clienteDao.listar();
			Messages.addGlobalInfo("Cliente salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar o cliente!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.excluir(cliente);
			clientes = clienteDao.listar();
			Messages.addGlobalInfo("Cliente excluído com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o cliente!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.editar(cliente);
			clientes = clienteDao.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o cliente!");
			erro.printStackTrace();
		}

	}

	public void retornaUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.listar();
		
	}
}
