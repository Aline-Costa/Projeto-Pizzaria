package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import br.edu.ifpe.pizzaria.model.BebidaModel;
import br.edu.ifpe.pizzaria.model.domain.Bebida;

@SuppressWarnings({ "serial", "deprecation" })
@ManagedBean
@ViewScoped
public class BebidaBean implements Serializable {

	private Bebida bebida;
	private List<Bebida> bebidas;

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public List<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	@PostConstruct
	public void listar() {

		BebidaModel bebidaModel = new BebidaModel();
		bebidas = bebidaModel.listarBebida();

	}

	public void novo() {

		bebida = new Bebida();
	}

	public void salvar() {

		BebidaModel bebidaModel = new BebidaModel();
		bebidaModel.registrarBebida(bebida);

		novo();
		bebidas = bebidaModel.listarBebida();

	}

	public void excluir(ActionEvent evento) {

		bebida = (Bebida) evento.getComponent().getAttributes().get("bebidaSelecionada");

		BebidaModel bebidaModel = new BebidaModel();
		bebidaModel.removerBebida(bebida);
		bebidas = bebidaModel.listarBebida();

	}

	public void editar(ActionEvent evento) {

		bebida = (Bebida) evento.getComponent().getAttributes().get("bebidaSelecionada");

		BebidaModel bebidaModel = new BebidaModel();
		bebidaModel.atualizarBebida(bebida);
		bebidas = bebidaModel.listarBebida();

	}
}