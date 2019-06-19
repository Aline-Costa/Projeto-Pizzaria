package br.edu.ifpe.pizzaria.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name = "cliente")
@NamedQueries({@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE cliente.codCliente = :codCliente"), @NamedQuery(name = "Cliente.buscarPorCodUsuario", query = "SELECT cliente FROM Cliente cliente WHERE cliente.usuario = :codUsuario")})
public class Cliente {
	
	@Id
	@Column(name = "cod_cliente")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codCliente;
	
	@Column(name = "rua", length = 50, nullable = false)
	private String rua;
	
	@Column(name = "num_casa", nullable = false)
	private Long numCasa;
	
	@Column(name = "bairro", length = 50, nullable = false)
	private String bairro;
	
	@Column(name = "telefone", length = 50, nullable = false)
	private String telefone;
	
	@Transient
	private String senhaSemCriptografia;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", nullable = false)
	private Usuario usuario;

	public Cliente(){
		
	}
	
	public Cliente(String rua, Long numCasa, String bairro, String telefone, Usuario usuario){
		
		this.rua = rua;
		this.numCasa = numCasa;
		this.bairro = bairro;
		this.telefone = telefone;
		this.usuario = usuario;
		
	}
	
	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Long getNumCasa() {
		return numCasa;
	}

	public void setNumCasa(Long numCasa) {
		this.numCasa = numCasa;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Cliente [codCliente=" + codCliente + ", rua=" + rua + ", numCasa=" + numCasa + ", bairro=" + bairro
				+ ", telefone=" + telefone + ", senhaSemCriptografia=" + senhaSemCriptografia + ", usuario=" + usuario
				+ "]";
	}
}
