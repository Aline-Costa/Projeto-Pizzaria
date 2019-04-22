package br.edu.ifpe.pizzaria.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@NamedQueries({@NamedQuery(name = "Cliente.listar", query = "SELECT cliente FROM Cliente cliente"),
	@NamedQuery(name = "Cliente.buscarPorCodigo", query = "SELECT cliente FROM Cliente cliente WHERE cliente.codCliente = :codCliente")})
public class Cliente extends Usuario implements Serializable {
	
	@Column(name = "cod_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long codCliente;

	public Cliente() {

	}

	public Cliente(String nome, String rua, Long numCasa, String bairro, String login,
			String senha, String email, Long codCliente) {

		super(nome, rua, numCasa, bairro, login, senha, email);

		this.codCliente = codCliente;
	}

	public Long getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

}