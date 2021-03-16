package br.com.dynamic.login;

public class Pagina {
	
	String pag;
	
	Pagina(String pagina) 
	{
		this.pag = pagina;
	} 
	
   /* Pagina sisconfin = new Pagina("/sisconfin.jsf");
    Pagina paginaNormal= new Pagina("/principal.jsf");

    Perfil admin = new Perfil();
    admin.temAcesso(paginaAdmin).temAcesso(paginaNormal);

    Perfil normal = new Perfil();
    normal.temAcesso(paginaNormal);

    Usuario homerSimpsons = new Usuario("homer99","senha");
    homerSimpsons.temPerfilDe(admin);
    */
    String temAcesso()
	{
		return this.pag;
	}


}
