package br.edu.ifpe.pizzaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

public class UsuarioDaoTest {

	@Test
	@Ignore
	public void salvar() {
		
		Usuario u2 = new Usuario();

		u2.setNome("Eduarda Campos");
		u2.setRua("E");
		u2.setNumCasa(15L);
		u2.setBairro("Cohab 2");
		u2.setLogin("eduarda");
		u2.setSenha("1234");
		u2.setEmail("eduarda@gmail.com");
		
		UsuarioDAO u2DAO = new UsuarioDAO();
		u2DAO.salvar(u2);

		Usuario u3 = new Usuario();

		u3.setNome("Pedro Santos");
		u3.setRua("Rui Barbosa");
		u3.setNumCasa(50L);
		u3.setBairro("Heliopólis");
		u3.setLogin("pedro");
		u3.setSenha("abcd");
		u3.setEmail("pedrinho@gmail.com");
		
		UsuarioDAO u3DAO = new UsuarioDAO();
		u3DAO.salvar(u3);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		for (Usuario usuario : resultado) {
			System.out.println(usuario.getNome() + "-" + usuario.getLogin());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		long cod_usuario = 3L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigo(cod_usuario);
		
		if(usuario == null){
			System.out.println("Nenhum registro encontrado!");
		}else{
			System.out.println(usuario.getNome() + "-" + usuario.getLogin());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		long cod_usuario = 1L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigo(cod_usuario);
		if(usuario == null){
			System.out.println("Nenhum registro encontrado!");
		}else{
			usuarioDAO.excluir(usuario);
			System.out.println("Usuário excluído com sucesso!");
			System.out.println(usuario.getNome() + "-" + usuario.getLogin());
			
		
		}
	}
	@Test
	@Ignore
	public void editar(){
		long cod_usuario = 10L;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCodigo(cod_usuario);
		if(usuario == null){
			System.out.println("Nenhum registro encontrado!");
		}else{
			usuario.setNome("Mari Eduarda Campos");
			usuarioDAO.editar(usuario);
			
		
		}
	}
}
