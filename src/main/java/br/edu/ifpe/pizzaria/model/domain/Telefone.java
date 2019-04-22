package br.edu.ifpe.pizzaria.model.domain;

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

@Entity
@NamedQueries({ @NamedQuery(name = "Telefone.buscarPorCodigo", query = "SELECT telefone FROM Telefone telefone WHERE codTelefone = :codTelefone")})
public class Telefone {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "cod_telefone")
	private Long codTelefone;
	
	@Column(length = 20, nullable = false)
	private String telefone;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario", nullable = false)
	private Usuario codUsuario;
	
	public Telefone(){
		
	}
	
	public Telefone(Long codTelefone, String telefone, Usuario codUsuario) {
		super();
		this.codTelefone = codTelefone;
		this.telefone = telefone;
		this.codUsuario = codUsuario;
	}

	public Long getCodTelefone() {
		return codTelefone;
	}
	
	public void setCodTelefone(Long codTelefone) {
		this.codTelefone = codTelefone;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Usuario getUsuario() {
		return codUsuario;
	}
	
	public void setUsuario(Usuario codUsuario) {
		this.codUsuario = codUsuario;
	}
	
	@Override
	public String toString() {
		return "Telefone [codTelefone=" + codTelefone + ", telefone=" + telefone + ", codUsuario=" + codUsuario + "]";
	}

}