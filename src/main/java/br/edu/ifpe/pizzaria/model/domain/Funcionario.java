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
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "funcionario")
@NamedQueries({@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codFuncionario = :codFuncionario")})
public class Funcionario implements Serializable{
	
	@Id
	@Column(name = "cod_funcionario")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codFuncionario;
	
	@Column(length = 30, nullable = false)
	private String funcao;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", nullable = false)
	private Usuario usuario;
	
	public Funcionario(){
		
	}
	
	public Funcionario(Long codFuncionario, String funcao, Usuario usuario){
		
		this.codFuncionario = codFuncionario;
		this.funcao = funcao;
		this.usuario = usuario;
		
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}