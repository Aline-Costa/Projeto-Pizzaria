package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="codUsuario")
@Table(name = "cliente")
@NamedQueries({
		@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE cliente.codCliente = :codCliente"), })
public class Cliente extends Usuario implements Serializable {

	
	@Column(name = "cod_cliente")
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

	public Cliente() {

	}

	public Cliente(Long codUsuario, String nome, String email, String senha, Long codCliente, String rua, Long numCasa,
			String bairro, String telefone, String senhaSemCriptografia) {
		super(nome, email, senha);
		this.codCliente = codCliente;
		this.rua = rua;
		this.numCasa = numCasa;
		this.bairro = bairro;
		this.telefone = telefone;
		this.senhaSemCriptografia = senhaSemCriptografia;
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

}
