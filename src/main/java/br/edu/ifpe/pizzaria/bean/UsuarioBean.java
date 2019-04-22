package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;

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
	
	@PostConstruct
	public void listar() {
		try {

			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarios = usuarioDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		usuario = new Usuario();
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.merge(usuario);

			novo();
			usuarios = usuarioDao.listar();
			Messages.addGlobalInfo("Usuário salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar o usuário!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.excluir(usuario);
			usuarios = usuarioDao.listar();
			Messages.addGlobalInfo("Usuário excluído com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir o usuário!");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDao = new UsuarioDAO();
			usuarioDao.editar(usuario);
			usuarios = usuarioDao.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar o usuário!");
			erro.printStackTrace();
		}
		
	}

}
