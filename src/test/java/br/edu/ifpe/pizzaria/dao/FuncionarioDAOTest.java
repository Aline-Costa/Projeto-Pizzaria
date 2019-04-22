package br.edu.ifpe.pizzaria.dao;

import br.edu.ifpe.pizzaria.model.dao.UsuarioDAO;
import br.edu.ifpe.pizzaria.model.domain.Funcionario;
import br.edu.ifpe.pizzaria.model.domain.Usuario;

public class FuncionarioDAOTest {
	
	public void salvar(){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		Usuario usuario = usuarioDAO.buscarPorCodigo(1L);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Maria");
		
	 //Incompleto
		
		
	}

}
