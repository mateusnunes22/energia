package br.com.dynamic.repo;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.entidade.FuncionarioId;
import br.com.dynamic.entidade.Usuario;
import br.com.dynamic.estrutura.Bean;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.util.HibernateUtil;
import br.com.dynamic.util.SessionUtil;


public class FuncionarioRepo {
	
	
	private Funcionario funcionario = new Funcionario();
	FuncionarioId funcionarioId = new FuncionarioId();
	
	public FuncionarioRepo() {
		
	}
	
	public FuncionarioRepo(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List<Funcionario> buscarTodos(){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Funcionario.class);
		q.addOrder(Order.desc("nome"));
		List<Funcionario> objeto = q.list();
		session.close();
		return objeto;
		
	}
	
	public Funcionario buscaPorId(int id) {

		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Funcionario.class);
		q.add(Restrictions.eq("id.idFuncionario", id));
		List<Funcionario> objeto = q.list();
		return objeto.get(0);

	}
	
	public List<Funcionario> buscarNome(){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Funcionario.class);
		q.add(Restrictions.like("nome",funcionario.getNome(), MatchMode.ANYWHERE ));
		q.addOrder(Order.desc("nome"));
		List<Funcionario> objeto = q.list();
		
			session.close();
		
		return objeto;
		
	}

public List<Funcionario> BuscaNomeLike(BeanMedio funcionario){
	
	Session session = HibernateUtil.getSession();
	Criteria q = session.createCriteria(Funcionario.class);
	q.add(Restrictions.like("nome", funcionario.getTexto(), MatchMode.ANYWHERE ));
	q.addOrder(Order.asc("nome"));
	
	List<Funcionario> objeto = q.list();
	
	return objeto;
	
}
	
	public String salvar() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();	
		boolean edit=true;
		String pagina = null;
		HttpSession session2 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Usuario usu = (Usuario) session2.getAttribute("currentUser");

		
		
			
			try {
				funcionario.getId().equals(new FuncionarioId());
			} catch (Exception e) {
				funcionarioId.setUsuarioIdUsuario(usu.getIdUsuario());
	            funcionario.setUsuario(usu);
	            funcionario.setId(funcionarioId);
	            edit=false;
			}
			
	
		
		
		try {
		
			session.merge(funcionario);
			t.commit();
			SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes
			funcionario = new Funcionario();
			
			if(edit)
			{
				pagina = "edit";
			}

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			SessionUtil.addErrorMessage("OperacaoFracasso");
			System.out.println("Dentista/Erro/"+e.getMessage());
		}finally{
			//fecha conexao
			session.close();
			
		}
		return pagina;
					
	}
	

}
