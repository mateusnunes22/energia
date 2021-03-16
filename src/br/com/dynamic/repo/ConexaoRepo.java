package br.com.dynamic.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.estrutura.ConexaoUtil;
import br.com.dynamic.util.HibernateUtil;


public class ConexaoRepo {

	private Conexao conexao = new Conexao();

	public ConexaoRepo() {

	}

	public ConexaoRepo(Conexao conexao) {
		this.conexao = conexao;
	}
	
	public ConexaoRepo(ConexaoUtil init) {
		conexao.setIdConexao(init.getIdConexao());
		conexao.setIp(init.getIp());
		conexao.setPorta(init.getPorta());
		conexao.setBaseDeDados(init.getBaseDeDados());
		conexao.setSsid(init.getSsid());
		conexao.setSenha(init.getSenha());
	}
	
public List<Conexao> buscarTodos(){
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Conexao.class);
		q.addOrder(Order.asc("idConexao"));
		List<Conexao> objeto = q.list();
		session.close();
		return objeto;
		
	}

	public String salvar() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
			session.merge(conexao);
			t.commit();
			
		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			System.out.println("Energia/Erro/" + e.getMessage());
		} finally {
			// fecha conexao
			session.close();
		}
		return null;

	}

}
