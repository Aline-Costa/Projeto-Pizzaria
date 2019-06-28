package br.edu.ifpe.pizzaria.bean;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.FuncionarioDAO;
import br.edu.ifpe.pizzaria.model.dao.MenuDAO;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;
import br.edu.ifpe.pizzaria.model.domain.Menu;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class LoginBean {

	private Usuario usuario;
	private Usuario usuarioLogado;
	private Usuario user;
	private Cliente cliente;
	private List<Cliente> clientes;
	private MenuModel modeloMenu;
	private List<Funcionario> funcionarios;
	private Funcionario funcionario;
	private String primeiroNome;

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

	public MenuModel getModeloMenu() {
		return modeloMenu;
	}

	public void setModeloMenu(MenuModel modeloMenu) {
		this.modeloMenu = modeloMenu;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public void autenticarUsuario() {
		try {
		
			MenuDAO menuDAO = new MenuDAO();
			List<Menu> lista = menuDAO.listar();
			modeloMenu = new DefaultMenuModel();

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());

			if (usuarioLogado == null) {
				Messages.addGlobalError("E-mail e/ou senha incorretos");

			}
			
			FacesContext context = FacesContext.getCurrentInstance();
			primeiroNome = usuarioLogado.getNome().split(" ")[0];

			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

			user = usuarioDAO.buscarPorCodigo(usuarioLogado.getCodUsuario());

			for (int i = 0; i < clientes.size(); i++) {

				if (user.getCodUsuario() == clientes.get(i).getUsuario().getCodUsuario()) {
					cliente = clientes.get(i);

				}
			}
			if (cliente != null) {

				for (Menu menu : lista) {
					if (menu.getCaminho() == null && !menu.getRotulo().equalsIgnoreCase("Cadastros")
							&& !menu.getRotulo().equalsIgnoreCase("Movimentações")) {

						DefaultSubMenu subMenu = new DefaultSubMenu(menu.getRotulo());

						for (Menu item : menu.getMenus()) {

							DefaultMenuItem menuItem = new DefaultMenuItem(item.getRotulo());
							menuItem.setUrl(item.getCaminho());

							subMenu.addElement(menuItem);
						}
						modeloMenu.addElement(subMenu);
					}
					
				}
				
			}

			if (cliente == null) {

				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarios = funcionarioDAO.listar();

				for (int i = 0; i < funcionarios.size(); i++) {

					if (user.getCodUsuario() == funcionarios.get(i).getUsuario().getCodUsuario()) {
						funcionario = funcionarios.get(i);
					}
				}
				if (funcionario.getFuncao().equalsIgnoreCase("Gerente")) {

					for (Menu menu : lista) {
						if (menu.getCaminho() == null && !menu.getRotulo().equalsIgnoreCase("Fazer Pedido")) {

							DefaultSubMenu subMenu = new DefaultSubMenu(menu.getRotulo());

							for (Menu item : menu.getMenus()) {

								DefaultMenuItem menuItem = new DefaultMenuItem(item.getRotulo());
								menuItem.setUrl(item.getCaminho());

								subMenu.addElement(menuItem);
							}
							modeloMenu.addElement(subMenu);
						}
					}
				}

				if (funcionario.getFuncao().equalsIgnoreCase("Atendente")) {

					for (Menu menu : lista) {
						if (menu.getCaminho() == null && !menu.getRotulo().equalsIgnoreCase("Cadastros")
								&& !menu.getRotulo().equalsIgnoreCase("Fazer Pedido")) {

							DefaultSubMenu subMenu = new DefaultSubMenu(menu.getRotulo());

							for (Menu item : menu.getMenus()) {

								DefaultMenuItem menuItem = new DefaultMenuItem(item.getRotulo());
								menuItem.setUrl(item.getCaminho());

								subMenu.addElement(menuItem);
							}
							modeloMenu.addElement(subMenu);
						}
					}
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
	
	public void deslogar() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        Faces.redirect("./pages/login.xhtml");
    }

}
