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
	private Usuario user;
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

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void autenticarUsuario() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				
			}
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
			
			user = usuarioDAO.buscarPorCodigo(usuarioLogado.getCodUsuario());

			for (int i = 0; i < clientes.size(); i++) {

				if (user.getCodUsuario() == clientes.get(i).getUsuario().getCodUsuario()) {
					cliente = clientes.get(i);
				}
			}
			
			Faces.redirect("./pages/principal.xhtml");
			
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
		
	}

	public void autenticarPrimeiroLogin() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("E-mail e/ou senha incorretos");
				return;
			}
			Faces.redirect("./pages/cliente1.xhtml");	

		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

}
