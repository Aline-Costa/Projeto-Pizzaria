package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import br.edu.ifpe.pizzaria.model.PizzaModel;
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

		PizzaModel pizzaModel = new PizzaModel();
		pizzas = pizzaModel.listarPizza();

	}

	public void novo() {

		pizza = new Pizza();
	}

	public void salvar() {

		PizzaModel pizzaModel = new PizzaModel();
		pizzaModel.registrarPizza(pizza);

		novo();
		pizzas = pizzaModel.listarPizza();

	}

	public void excluir(ActionEvent evento) {

		pizza = (Pizza) evento.getComponent().getAttributes().get("pizzaSelecionada");

		PizzaModel pizzaModel = new PizzaModel();
		pizzaModel.removerPizza(pizza);
		pizzas = pizzaModel.listarPizza();

	}

	public void editar(ActionEvent evento) {

		pizza = (Pizza) evento.getComponent().getAttributes().get("pizzaSelecionada");

		PizzaModel pizzaModel = new PizzaModel();
		pizzaModel.atualizarPizza(pizza);
		pizzas = pizzaModel.listarPizza();

	}

}
