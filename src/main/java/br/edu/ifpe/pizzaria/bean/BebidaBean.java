package br.edu.ifpe.pizzaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import br.edu.ifpe.pizzaria.model.dao.BebidaDAO;
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
		try {

			BebidaDAO bebidaDao = new BebidaDAO();
			bebidas = bebidaDao.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as bebidas.");
			erro.printStackTrace();
		}
	}

	public void novo() {

		bebida = new Bebida();
	}

	public void salvar() {
		try {
			BebidaDAO bebidaDao = new BebidaDAO();
			bebidaDao.merge(bebida);

			novo();
			bebidas = bebidaDao.listar();
			Messages.addGlobalInfo("Bebida salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao salvar a bebida!");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			bebida = (Bebida) evento.getComponent().getAttributes().get("bebidaSelecionada");

			BebidaDAO bebidaDao = new BebidaDAO();
			bebidaDao.excluir(bebida);
			bebidas = bebidaDao.listar();
			Messages.addGlobalInfo("Bebida excluída com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao excluir a bebida!");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		try {
			bebida = (Bebida) evento.getComponent().getAttributes().get("bebidaSelecionada");

			BebidaDAO bebidaDAO = new BebidaDAO();
			bebidaDAO.editar(bebida);
			bebidas = bebidaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao editar a bebida!");
			erro.printStackTrace();
		}

	}
}