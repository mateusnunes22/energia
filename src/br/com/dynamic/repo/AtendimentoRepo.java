package br.com.dynamic.repo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.ClienteId;
import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.AtendimentoId;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.entidade.Usuario;
import br.com.dynamic.estrutura.Bean;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.util.HibernateUtil;
import br.com.dynamic.util.SessionUtil;


public class AtendimentoRepo {
	
	
	private Atendimento atendimento = new Atendimento();
	private Cliente cliente = new Cliente();
	AtendimentoId atendimentoId = new AtendimentoId();
	
	public AtendimentoRepo() {
		
	}
	
	public AtendimentoRepo(Atendimento atendimento) {
		this.atendimento = atendimento;
	}


	public List<Atendimento> getLista(){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Atendimento.class);
		List<Atendimento> objeto = q.list();
		return objeto;
		
	}
	public List<Atendimento> getForMac(){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Atendimento.class);
		q.add(Restrictions.eq("mac", atendimento.getMac()));
		List<Atendimento> objeto = q.list();
		return objeto;
		
	}
	
public List<Atendimento> getForIp(String ip){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Atendimento.class);
		q.add(Restrictions.eq("ip", ip));
		List<Atendimento> objeto = q.list();
		return objeto;
		
	}

	public List<Atendimento> getForId(int id){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Atendimento.class);
		q.add(Restrictions.eq("id.idAtendimento", id));
		List<Atendimento> objeto = q.list();
		return objeto;
		
	}
	
	public List<Atendimento> buscarForgId(int id){
		// Cria sessao com o banco
		Session session = HibernateUtil.getSession();
		// Cria query de consulta
		Query q = session.createQuery("from Atendimento u where u.id.idAtendimento = :idAtendimento");
		// Seta o parametro pelo :		
		q.setParameter("idAtendimento", id);
		// joga o resultado da pesquisa para uma lista
		List<Atendimento> objeto = q.list();
		session.close();
		return objeto;
	} 

	

	
	public String salvar() throws IllegalStateException {
		// cria sessao com banco e transacao
		FuncionarioRepo funcionarioRepo = new FuncionarioRepo();
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();	
		
		//HttpSession session2 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		//Usuario usu = (Usuario) session2.getAttribute("currentUser");
		
		UsuarioRepo usuarioRepo = new UsuarioRepo();
		
		Usuario usu = new Usuario();
		usu = usuarioRepo.buscarId(1).get(0);	
		
		try {
				
			try {
				boolean isNew = atendimento.getId().getUsuarioIdUsuario()==1;
			} catch (Exception e) {
				atendimentoId.setUsuarioIdUsuario(usu.getIdUsuario());
				atendimento.setId(atendimentoId);
			}
			
			atendimento.setUsuario(usu);
			atendimento.setData(new Date());
			session.merge(atendimento);
			t.commit();
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			System.out.println("DentistaEstetica/Erro/"+e.getMessage());
			
		}finally{
			//fecha conexao
			session.close();	
		}
		return null;
					
	}
	

}
