package br.edu.ifpe.pizzaria.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity

@Table(name = "usuario_cliente")
@NamedQueries({@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE cliente.codCliente = :codCliente")})
public class Cliente {
	
	@Id
	@Column(name = "cod_cliente", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codCliente;
	
	@Column(name = "nome", length = 80, nullable = false)
	private String nome;
	
	@Column(name = "rua", length = 50, nullable = false)
	private String rua;
	
	@Column(name = "num_casa", nullable = false)
	private Long numCasa;
	
	@Column(name = "bairro", length = 50, nullable = false)
	private String bairro;
	
	@Column(name = "telefone", length = 50, nullable = false)
	private String telefone;
	
	@Column(name = "senha", length = 15, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	@Column(name = "email", length = 80, nullable = false, unique = true)
	private String email;

	public Cliente(){
		
	}
	
	public Cliente(String nome, String rua, Long numCasa, String bairro, String telefone, String senha, String email){
		
		this.nome =  nome;
		this.rua = rua;
		this.numCasa = numCasa;
		this.bairro = bairro;
		this.telefone = telefone;
		this.senha = senha;
		this.email = email;
		
	}
	
	
	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codCliente + ", nome=" + nome + ", rua=" + rua + ", numCasa=" + numCasa
				+ ", bairro=" + bairro + " senha=" + senha
				+ ", email=" + email + "]";
	}

}
