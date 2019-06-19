package br.edu.ifpe.pizzaria.dao;

import java.util.List;

import org.junit.Test;

import br.edu.ifpe.pizzaria.model.dao.MenuDAO;
import br.edu.ifpe.pizzaria.model.domain.Menu;

public class MenuDAOTest {

	@Test
	public void listar() {

		MenuDAO menuDAO = new MenuDAO();

		List<Menu> lista = menuDAO.listar();

		for (Menu menu : lista) {
			if (menu.getCaminho() == null) {

				System.out.println(menu.getRotulo() + "-" + menu.getCaminho());

				for (Menu item : menu.getMenus()) {

					System.out.println("\t" + item.getRotulo() + "-" + item.getCaminho());

				}
			}
		}

	}

}
