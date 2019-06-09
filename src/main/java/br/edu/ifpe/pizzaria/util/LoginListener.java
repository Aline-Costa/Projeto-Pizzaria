package br.edu.ifpe.pizzaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.edu.ifpe.pizzaria.bean.LoginBean;
import br.edu.ifpe.pizzaria.model.domain.Cliente;

@SuppressWarnings("serial")
public class LoginListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		
		String paginaAtual = Faces.getViewId();
		System.out.println("Página Atual: " + paginaAtual);
		
		boolean ehPagLogin = paginaAtual.contains("login.xhtml");
		
		if(!ehPagLogin){
			
			LoginBean loginBean = Faces.getSessionAttribute("loginBean");
			
			if(loginBean == null){
				Faces.navigate("/pages/login.xhtml");
				return;
			}
			Cliente cliente = loginBean.getClienteLogado();
			if(cliente == null){
				Faces.navigate("/pages/login.xhtml");
				return;	
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RESTORE_VIEW;
	}

}
