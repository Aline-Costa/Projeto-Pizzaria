package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "usuario_funcionario")
@NamedQueries({@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codFuncionario = :codFuncionario")})
public class Funcionario implements Serializable{
	
	@Id
	@Column(name = "cod_funcionario")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codFuncionario;
	
	@Column(name = "nome", length = 80, nullable = false)
	private String nome;
	
	@Column(length = 30, nullable = false)
	private String funcao;
	
	@Column(name = "senha", length = 15, nullable = false)
	private String senha;
	
	@Column(name = "email", length = 80, nullable = false, unique = true)
	private String email;
	
	public Funcionario(){
		
	}
	
	public Funcionario(Long codFuncionario, String nome, String funcao, String senha, String email){
		
		this.codFuncionario = codFuncionario;
		this.nome = nome;
		this.funcao = funcao;
		this.senha = senha;
		this.email = email;
		
	}

	public Long getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Long codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
	
}