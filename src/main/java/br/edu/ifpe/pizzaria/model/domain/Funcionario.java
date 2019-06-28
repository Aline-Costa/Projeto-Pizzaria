package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="codUsuario")
@Table(name = "funcionario")
@NamedQueries({
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codFuncionario = :codFuncionario") })
public class Funcionario extends Usuario implements Serializable {

	@Column(name = "cod_funcionario")
	private Long codFuncionario;

	@Column(length = 30, nullable = false)
	private String funcao;

	public Funcionario() {

	}

	public Funcionario(Long codFuncionario, String funcao, String nome, String email, String senha) {
		super(nome, email, senha);
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