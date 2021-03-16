package br.com.dynamic.login;

import java.util.Date;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.dynamic.entidade.Usuario;
import br.com.dynamic.estrutura.ThreadBroadcastForcada;
import br.com.dynamic.estrutura.ThreadConsulta;
import br.com.dynamic.scheduler.Main;
import br.com.dynamic.thread.ThreadRotinaGeral;

public class AuthorizationListener implements PhaseListener {

	ThreadRotinaGeral enviarMensagem = new ThreadRotinaGeral();
	ThreadBroadcastForcada BuscarWEC = new ThreadBroadcastForcada();
	
public void afterPhase(PhaseEvent event) {
FacesContext facesContext = event.getFacesContext();
//adiquirindo o FacesContext.
String currentPage = facesContext.getViewRoot().getViewId();
Usuario user = null;
//armazenando a p�gina que fez a requisi��o. (a string da p�g. atual ex: "/pag.jsf")
boolean isLoginPage = (currentPage.lastIndexOf("/login.jsp") > -1);
//fazendo a verifica��o mais b�sica de todas... se � a p�gina de login.
HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
if(isLoginPage)
{
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", user);
}
else
{	
	user = (Usuario) session.getAttribute("currentUser");
	
	System.out.println(enviarMensagem.getState());
	if(enviarMensagem.getState().equals(Thread.State.TERMINATED)){
		enviarMensagem = new ThreadRotinaGeral();
	}

	if(enviarMensagem.getState().equals(Thread.State.NEW)){
		enviarMensagem.start();
	}
	System.out.println(enviarMensagem.getState());
	
	System.out.println(BuscarWEC.getState());
	if(BuscarWEC.getState().equals(Thread.State.TERMINATED)){
		BuscarWEC = new ThreadBroadcastForcada();
	}

	if(BuscarWEC.getState().equals(Thread.State.NEW)){
		//BuscarWEC.start();
	}
	System.out.println(BuscarWEC.getState());
	
}
//adquirindo a sess�o (essa mesma onde voc� dever� guardar seu usu�rio no n�vel de sess�o com descritor currentUser).


//apenas recuperando o valor da sess�o.
if (!isLoginPage && user == null) {
	System.out.println("Factory/Login/"+new Date(new Date().getTime()-10800000));
NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
nh.handleNavigation(facesContext, null, "login");
//bem, se n�o est� logado redireciona pra l�gica que (navigatio rule) atende a loginPage
} else {
//verificar se o usuario atual tem acesso a p�gina atual.

	//System.out.println(currentPage);
	//System.out.println(user.getPerfil());
	
boolean temAcesso=false;

try {
	temAcesso= user.temAcesso(currentPage, user.getPerfil());
} catch (Exception e) {
	temAcesso=true;
}
try {
	System.out.println("Factory/"+temAcesso+currentPage.toString()+"/"+user.getNomeUsuario()+"/"+ new Date(new Date().getTime()-10800000));
} catch (Exception e) {
	System.out.println("Factory/"+temAcesso+currentPage.toString()+"/"+"Sem usuario"+"/"+ new Date(new Date().getTime()-10800000));
}


if (temAcesso){
	
	

}
else {
	
	//aqui a logica de n�o ter acesso... redicione novamente? fa�a algo... ???
	NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
	nh.handleNavigation(facesContext, null, "login");
	
}
}

//caso contr�rio o jsf passa tranquilamente por aqui!!!

}


public void beforePhase(PhaseEvent event) {
	
}

public PhaseId getPhaseId() {
return PhaseId.RESTORE_VIEW;
}
}