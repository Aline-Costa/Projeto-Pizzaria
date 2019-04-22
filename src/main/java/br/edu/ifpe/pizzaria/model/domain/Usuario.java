package br.edu.ifpe.pizzaria.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario")
@NamedQueries({@NamedQuery(name = "Usuario.buscarPorCodigo", query = "SELECT usuario FROM Usuario usuario WHERE usuario.codUsuario = :codUsuario")})
public class Usuario {
	
	@Id
	@Column(name = "cod_usuario", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long codUsuario;
	
	@Column(name = "nome", length = 80, nullable = false)
	protected String nome;
	
	@Column(name = "rua", length = 50, nullable = false)
	protected String rua;
	
	@Column(name = "num_casa", nullable = false)
	protected Long numCasa;
	
	@Column(name = "bairro", length = 50, nullable = false)
	protected String bairro;
	
	@Column(name = "login", length = 100, nullable = false, unique = true)
	protected String login;
	
	@Column(name = "senha", length = 15, nullable = false)
	protected String senha;
	
	@Column(name = "email", length = 80, nullable = false, unique = true)
	protected String email;

	public Usuario(){
		
	}
	
	public Usuario(String nome, String rua, Long numCasa, String bairro, String login, String senha, String email){
		
		this.nome =  nome;
		this.rua = rua;
		this.numCasa = numCasa;
		this.bairro = bairro;
		this.login = login;
		this.senha = senha;
		this.email = email;
		
	}
	
	public Long getCodUsuario() {
		return codUsuario;

	}

	public void setCodUsuario(Long codUsuario) {
		this.codUsuario = codUsuario;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	@Override
	public String toString() {
		return "Usuario [codUsuario=" + codUsuario + ", nome=" + nome + ", rua=" + rua + ", numCasa=" + numCasa
				+ ", bairro=" + bairro + ", login=" + login + ", senha=" + senha
				+ ", email=" + email + "]";
	}

}
