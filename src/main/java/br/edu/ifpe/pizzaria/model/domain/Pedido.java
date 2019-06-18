package br.edu.ifpe.pizzaria.model.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({@NamedQuery(name = "Pedido.buscarPorCodigo", query = "SELECT pedido FROM Pedido pedido WHERE codPedido = :codPedido")})
public class Pedido implements Serializable{
	
	@Id
	@Column(name = "cod_pedido", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codPedido;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", nullable = false)
	private Cliente codCliente;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date horario;

	
	@Column(name = "valor_total", precision = 7, scale = 2)
	private BigDecimal valorTotal;
	
	@OneToMany(
			mappedBy = "pedido",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<PedidoBebida> bebidas = new ArrayList<>();
	
	@OneToMany(
			mappedBy = "pedido",
			cascade = CascadeType.ALL,
			orphanRemoval = true
		)
	private List<PedidoPizza> pizzas = new ArrayList<>();
	
	public Pedido(){
		
	}

	public Pedido(Long codPedido, Cliente codCliente, List<PedidoBebida> bebidas,
			List<PedidoPizza> pizzas) {
		
		this.codPedido = codPedido;
		this.codCliente = codCliente;
		this.bebidas = bebidas;
		this.pizzas = pizzas;
	}

	public Long getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}

	public Cliente getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Cliente codCliente) {
		this.codCliente = codCliente;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<PedidoBebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<PedidoBebida> bebidas) {
		this.bebidas = bebidas;
	}

	public List<PedidoPizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<PedidoPizza> pizzas) {
		this.pizzas = pizzas;
	}
	
}
