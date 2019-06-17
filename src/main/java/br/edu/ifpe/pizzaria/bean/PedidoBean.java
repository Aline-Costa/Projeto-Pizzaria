package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.edu.ifpe.pizzaria.model.dao.ClienteDAO;
import br.edu.ifpe.pizzaria.model.dao.PedidoDAO;
import br.edu.ifpe.pizzaria.model.dao.PizzaDAO;
import br.edu.ifpe.pizzaria.model.domain.Cliente;
import br.edu.ifpe.pizzaria.model.domain.Pedido;
import br.edu.ifpe.pizzaria.model.domain.PedidoBebida;
import br.edu.ifpe.pizzaria.model.domain.PedidoPizza;
import br.edu.ifpe.pizzaria.model.domain.Pizza;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {

	private Pedido pedido;
	private List<Pizza> pizzas;
	private List<PedidoPizza> pedidosPizzas;
	private List<Pedido> pedidos;
	private List<Cliente> clientes;

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public List<PedidoPizza> getPedidosPizzas() {
		return pedidosPizzas;
	}

	public void setPedidosPizzas(List<PedidoPizza> pedidosPizzas) {
		this.pedidosPizzas = pedidosPizzas;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	@PostConstruct
	public void listar() {
		try {

			pedido = new Pedido();
			pedido.setValorTotal(new BigDecimal("0.00"));

			PizzaDAO pizzaDao = new PizzaDAO();
			pizzas = pizzaDao.listar();

			pedidosPizzas = new ArrayList<>();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de pedidos.");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pedido = new Pedido();
			pedido.setValorTotal(new BigDecimal("0.00"));
			
			PizzaDAO pizzaDAO = new PizzaDAO();
			pizzas = pizzaDAO.listar();
			pedidosPizzas = new ArrayList<>();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de pedidos");
			erro.printStackTrace();
		}
	}
	
	public void listarPedidos(){
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		pedidos = pedidoDAO.listar();
	}

	public void adicionar(ActionEvent evento) {

		Pizza pizza = (Pizza) evento.getComponent().getAttributes().get("pizzaSelecionada");

		int achou = -1;

		for (int pos = 0; pos < pedidosPizzas.size(); pos++) {
			if (pedidosPizzas.get(pos).getPizza().equals(pizza)) {
				achou = pos;
			}
		}

		if (achou < 0) {

			PedidoPizza pedidoPizza = new PedidoPizza();

			pedidoPizza.setPreco(pizza.getPreco());
			pedidoPizza.setPizza(pizza);
			pedidoPizza.setQtd(1L);

			pedidosPizzas.add(pedidoPizza);

		} else {

			PedidoPizza pedidoPizza = pedidosPizzas.get(achou);

			pedidoPizza.setQtd(new Long(pedidoPizza.getQtd() + 1L + ""));
			pedidoPizza.setPreco(pizza.getPreco().multiply(new BigDecimal(pedidoPizza.getQtd())));

		}

		calculaTotal();
	}

	public void remover(ActionEvent evento) {

		PedidoPizza pedidoPizza = (PedidoPizza) evento.getComponent().getAttributes().get("pedidoSelecionado");

		int achou = -1;

		for (int pos = 0; pos < pedidosPizzas.size(); pos++) {
			if (pedidosPizzas.get(pos).getPizza().equals(pedidoPizza.getPizza())) {
				achou = pos;
			}
		}

		if (achou > -1 && pedidoPizza.getQtd() > 1) {

			pedidoPizza.setQtd(new Long(pedidoPizza.getQtd() - 1 + ""));
			pedidoPizza.setPreco(pedidoPizza.getPreco().subtract(pedidoPizza.getPizza().getPreco()));

		} else {
			pedidosPizzas.remove(achou);
		}

		calculaTotal();
	}

	public void calculaTotal() {

		pedido.setValorTotal(new BigDecimal("0.00"));

		for (int pos = 0; pos < pedidosPizzas.size(); pos++) {
			PedidoPizza pedidoPizza = pedidosPizzas.get(pos);

			pedido.setValorTotal(pedido.getValorTotal().add(pedidoPizza.getPreco()));
		}
	}

	public void finalizar() {

		try {

			pedido.setHorario(new Date());
		
			ClienteDAO clienteDAO =  new ClienteDAO();
			clientes = clienteDAO.listar(); 
			

		} catch (RuntimeException erro) {
			
			erro.printStackTrace();
		}

	}

	public void salvar() {
		try {
			if (pedido.getValorTotal().signum() == 0) {
				Messages.addGlobalError("Informe pelo menos um produto para a compra.");
				return;
			}
			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.salvar(pedido, pedidosPizzas);
			
			pedido = new Pedido();
			pedido.setValorTotal(new BigDecimal("0.00"));
			
			PizzaDAO pizzaDAO = new PizzaDAO();
			pizzas = pizzaDAO.listar();
			pedidosPizzas = new ArrayList<>();
			
			Messages.addGlobalInfo("Pedido realizado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar pedido!");
			erro.printStackTrace();
		}

	}

}
