package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoBebida implements Serializable{
	
	@Id
	@ManyToOne
	private Pedido pedido;
	
	@Id
	@ManyToOne
	private Bebida bebida;
	
	@Column(precision = 7, scale = 2 )
	private BigDecimal preco;
	
	@Column(name = "quantidade", nullable = false)
	private Long qtd;
	
	
	public PedidoBebida(){
		
	}
	
	public PedidoBebida(Pedido pedido, Bebida bebida) {
		super();
		this.pedido = pedido;
		this.bebida = bebida;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
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



