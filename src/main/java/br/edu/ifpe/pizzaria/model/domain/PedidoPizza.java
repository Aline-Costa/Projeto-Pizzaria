package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoPizza implements Serializable{
	
	@Id
	@ManyToOne
	private Pedido pedido;
	
	@Id
	@ManyToOne
	private Pizza pizza;

	@Column(precision = 7, scale = 2 )
	private BigDecimal preco;
	
	@Column(name = "quantidade", nullable = false)
	private Long qtd;
	
	public PedidoPizza(){
		
	}

	public PedidoPizza(Pedido pedido, Pizza pizza) {
		super();
		this.pedido = pedido;
		this.pizza = pizza;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}	

}