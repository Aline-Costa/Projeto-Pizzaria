package br.edu.ifpe.pizzaria.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.FuncionarioDAO;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Usuario usuario;
	private Usuario usuarioLogado;
	private Cliente cliente;
	private List<Cliente> clientes;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void iniciar() {
		usuario = new Usuario();
	}

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

	public Usuario autenticarUsuario() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				return null;
			}
			Faces.redirect("./pages/principal.xhtml");
			
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
		return usuarioLogado;
	}

	public void autenticarPrimeiroLogin() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/pedido1.xhtml");
			
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public Cliente retornaCliente(){
		
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		clientes = clienteDAO.listar();
	
		UsuarioDAO usuarioDAO =  new UsuarioDAO();
		usuario = usuarioDAO.buscarPorCodigo(usuarioLogado.getCodUsuario());
		
		for(int i = 0; i < clientes.size(); i++){
			
			if(usuario.getCodUsuario() == clientes.get(i).getUsuario().getCodUsuario()){
				cliente = clientes.get(i);
			}
		}
		
		return cliente;
		
	}

}
