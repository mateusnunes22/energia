package br.com.dynamic.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import br.com.dynamic.repo.UsuarioRepo;
import br.com.dynamic.util.SessionUtil;
import br.com.dynamic.entidade.*;

public class UsuarioBean {

	private Usuario usuario = new Usuario();
	public List<Usuario> lista =  new ArrayList<Usuario>();
	private UsuarioRepo usuarioRepo;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getLista() {
		usuarioRepo = new UsuarioRepo(usuario);
		return usuarioRepo.getLista();
	}


		public void setLista(List<Usuario> lista) {
			this.lista = lista;
		}
	
	public String salvar() {
		
		usuarioRepo = new UsuarioRepo(usuario);
		
		usuarioRepo.salvar();
		
		return null;
	}
	
	public String telaStatus() {
		
		return "status";
	}
	
	
	public String logar() {
		
		usuarioRepo = new UsuarioRepo(usuario);
		Usuario usuarioLogin = new Usuario(); 
		
		try{
			
			usuario.setPerfil(usuarioRepo.buscarNome(usuario.getNomeUsuario()).get(0).getPerfil());
			
			usuarioLogin = usuarioRepo.buscarNome(usuario.getNomeUsuario()).get(0);
			
			if(usuarioLogin.getSenha().equalsIgnoreCase(usuario.getSenha()))
			{
			    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", usuarioLogin);
			    
			    usuario = new Usuario();
			    
				return "logado";
			}
			else
			{
				SessionUtil.addErrorMessage("SenhaIncorreta");
			}
			
			
			
			

		}catch (Exception e) {
					SessionUtil.addErrorMessage("UsuarioInvalido");
			}
		
			
			
			return null;
			
		}
	
	
	
}
