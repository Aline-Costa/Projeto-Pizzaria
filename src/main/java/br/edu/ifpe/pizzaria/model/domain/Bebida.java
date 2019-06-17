package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;
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
@NamedQueries({@NamedQuery(name = "Bebida.buscarPorCodigo", query = "SELECT bebida FROM Bebida bebida WHERE bebida.codBebida = :codBebida")})
public class Bebida implements Serializable{
	
	@Id
	@Column(name = "cod_bebida")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codBebida;
	
	@Column(length = 40, nullable = false)
	private String nome;
	
	@Column(length = 10, nullable = false)
	private String tamanho;
	
	@Column(precision = 7, scale = 2 )
	private BigDecimal preco;
	
	@OneToMany(
			mappedBy = "bebida",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<PedidoBebida> pedidos = new ArrayList<>();
	
	
	public Bebida(){
		
	}
	
	public Bebida(Long codBebida, String nome, String tamanho, BigDecimal preco, List<PedidoBebida> pedidos) {
		super();
		this.codBebida = codBebida;
		this.nome = nome;
		this.tamanho = tamanho;
		this.preco = preco;
		this.pedidos = pedidos;
	}

	public Long getCodBebida() {
		return codBebida;
	}
	public void setCodBebida(Long codBebida) {
		this.codBebida = codBebida;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public List<PedidoBebida> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoBebida> pedidos) {
		this.pedidos = pedidos;
	}

}
