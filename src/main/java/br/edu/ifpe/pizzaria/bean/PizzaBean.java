package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import br.edu.ifpe.pizzaria.model.dao.PizzaDAO;
import br.edu.ifpe.pizzaria.model.domain.Pizza;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class PizzaBean implements Serializable {

	private Pizza pizza;
	private List<Pizza> pizzas;

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@PostConstruct
	public void listar() {
		try {

			PizzaDAO pizzaDao = new PizzaDAO();
			pizzas = pizzaDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pizzas.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		pizza = new Pizza();
	}

	public void salvar() {
		try {
			PizzaDAO pizzaDao = new PizzaDAO();
			pizzaDao.merge(pizza);

			novo();
			pizzas = pizzaDao.listar();
			Messages.addGlobalInfo("Pizza salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar a pizza!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			pizza = (Pizza) evento.getComponent().getAttributes().get("pizzaSelecionada");

			PizzaDAO pizzaDao = new PizzaDAO();
			pizzaDao.excluir(pizza);
			pizzas = pizzaDao.listar();
			Messages.addGlobalInfo("Pizza excluída com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir a pizza!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			pizza = (Pizza) evento.getComponent().getAttributes().get("pizzaSelecionada");

			PizzaDAO pizzaDao = new PizzaDAO();
			pizzaDao.editar(pizza);
			pizzas = pizzaDao.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar a pizza!");
			erro.printStackTrace();
		}

	}

}
