package br.edu.ifpe.pizzaria.model.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({@NamedQuery(name = "Pizza.buscarPorCodigo", query = "SELECT pizza FROM Pizza pizza WHERE pizza.codPizza = :codPizza")})
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cod_pizza")
	private Long codPizza;

	@Column(length = 30, nullable = false)
	private String nome;

	@Column(length = 200, nullable = false)
	private String descricao;

	@Column(length = 10, nullable = false)
	private String tamanho;

	@Column(precision = 7, scale = 2)
	private BigDecimal preco;
	
	@OneToMany(
			mappedBy = "pizza",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<PedidoPizza> pedidos = new ArrayList<>();
	
	
	public Pizza(){
		
	}

	public Pizza(Long codPizza, String nome, String descricao, String tamanho, BigDecimal preco,
			List<PedidoPizza> pedidos) {
		super();
		this.codPizza = codPizza;
		this.nome = nome;
		this.descricao = descricao;
		this.tamanho = tamanho;
		this.preco = preco;
		this.pedidos = pedidos;
		
	}


	public Long getCodPizza() {
		return codPizza;
	}

	public void setCodPizza(Long codPizza) {
		this.codPizza = codPizza;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public List<PedidoPizza> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoPizza> pedidos) {
		this.pedidos = pedidos;
	}
	
}