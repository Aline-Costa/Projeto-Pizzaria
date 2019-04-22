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

import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "funcionario")
@NamedQueries({@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codFuncionario = :codFuncionario")})
public class Funcionario extends Usuario implements Serializable{
	
	
	@Column(name = "cod_funcionario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codFuncionario;
	
	
	@Column(length = 30, nullable = false)
	private String funcao;
	
	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String rua, Long numCasa, String bairro, String login, String senha, String email,Long codFuncionario, String funcao  ){
		
		super(nome, rua, numCasa, bairro, login, senha, email);
		
		this.codFuncionario = codFuncionario;
		this.funcao = funcao;
	}
	
	public Long getCodFuncionario() {
		return codFuncionario;
	}
	public void setCodFuncionario(Long codFuncionario) {
		this.codFuncionario = codFuncionario;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
}