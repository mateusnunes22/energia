package br.com.dynamic.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.BoldAction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.dynamic.util.HibernateUtil;
import br.com.dynamic.util.SessionUtil;
import br.com.dynamic.entidade.*;

public class UsuarioRepo {

	private Usuario usuario;
	public List<Usuario> lista;
	public String desc;

	public UsuarioRepo() {

	}

	public UsuarioRepo(Usuario usuario) {
		this.usuario = usuario;
		this.lista = new ArrayList<Usuario>();
		this.desc = "";
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Usuario> buscarId(Integer id) {
		// Cria sessao com o banco
		Session session = HibernateUtil.getSession();
		// Cria query de consulta
		Query q = session
				.createQuery("from Usuario u where u.idUsuario = :idUsuario");
		// Seta o parametro pelo :
		q.setParameter("idUsuario", id);
		// joga o resultado da pesquisa para uma lista
		List<Usuario> objeto = q.list();
		return objeto;
	}

	public List<Usuario> buscarNome(String nomeUsuario) {
		// Cria sessao com o banco
		Session session = HibernateUtil.getSession();
		// Cria query de consulta
		Query q = session
				.createQuery("from Usuario u where u.nomeUsuario = :nomeUsuario ");
		// Seta o parametro pelo :
		q.setParameter("nomeUsuario", nomeUsuario);
		// joga o resultado da pesquisa para uma lista
		List<Usuario> objeto = q.list();

		objeto.get(0);

		return objeto;
	}

	public String pesquisaUsuario() {
		int y = -1;
		try {
			y = Integer.parseInt(desc);

		} catch (Exception e) {

		}

		if (desc.equals("")) {
			lista = buscaTodos();
		} else if (y >= 0) {
			System.out.println(y);
			lista = buscarId(y);
		} else {
			lista = buscarNome(desc);
			System.out.println(desc);
		}

		return null;
	}

	public List<Usuario> getLista() {
		if (desc.equals("")) {
			lista = buscaTodos();
			return lista;
		} else {
			return lista;
		}
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public List<Usuario> buscaTodos() {

		Session session = HibernateUtil.getSession();
		Query q = session.createQuery("from Usuario");

		List<Usuario> objeto = q.list();
		return objeto;

	}

	public List<Integer> buscarPerfil(int id) {

		Session session = HibernateUtil.getSession();

		Criteria q = session.createCriteria(Usuario.class);

		String perfil = buscarId(id).get(0).getPerfil();

		q.add(Restrictions.like("perfil", perfil.substring(3), MatchMode.END));

		List<Usuario> objeto = q.list();

		List<Integer> listaId = new ArrayList<Integer>();

		for (int i = 0; i < objeto.size(); i++) {

			listaId.add(objeto.get(i).getIdUsuario());

		}

		return listaId;

	}

	public String salvar() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		String nomeUsuario = usuario.getNomeUsuario();

		boolean salvar = true;
		boolean salvar3 = false;

		System.out.println(UsuarioRepo.this.lista.size());
		System.out.println(usuario.getNomeUsuario());
		System.out.println(nomeUsuario);

		try {
			System.out.println("1");
			for (int i = 0; i < UsuarioRepo.this.lista.size(); i++) {
				System.out.println("2");
				System.out.println(UsuarioRepo.this.lista.get(i)
						.getNomeUsuario());

				if (nomeUsuario.equalsIgnoreCase(UsuarioRepo.this.lista.get(i)
						.getNomeUsuario())) {
					System.out.println("3");
					salvar = false;
				}
			}
			System.out.println("4");

			int cont = 0;

			for (int i = 0; i < usuario.getEmail().length(); i++) {
				String teste = String.valueOf(usuario.getEmail().charAt(i));

				if (teste.equals("@")) {

					cont++;

				}

				if (cont == 1) {
					salvar3 = true;
				} else {
					salvar3 = false;
				}

			}

			if (salvar) {
				System.out.println("6");
				if (salvar3) {
					System.out.println("7");

					usuario.setPerfil("maximo");
					session.merge(usuario);
					t.commit();
					System.out.println("8");
					SessionUtil.addSuccessMessage("OperacaoSucesso");
					// limpa objeto funcionario para novas insercoes
					System.out.println("9");

					usuario = new Usuario();
					System.out.println("12");
				} else {
					SessionUtil.addErrorMessage("EmailInvalido");
				}
			} else {
				System.out.println("7");
				SessionUtil.addErrorMessage("UsuarioJaCadastrado");
			}
		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			SessionUtil.addErrorMessage("OperacaoFracasso");
		}

		finally {
			// fecha conexao
			session.close();

		}

		return null;

	}

}
