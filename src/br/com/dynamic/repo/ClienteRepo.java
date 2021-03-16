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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sun.org.apache.xpath.internal.operations.Equals;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.AtendimentoId;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.ClienteId;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.entidade.Usuario;
import br.com.dynamic.estrutura.Bean;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.util.HibernateUtil;
import br.com.dynamic.util.SessionUtil;

public class ClienteRepo {

	private Cliente cliente = new Cliente();
	ClienteId clienteId = new ClienteId();
	AtendimentoRepo atendimentoRepo;
	ArrayList<Atendimento> atendimento = new ArrayList<Atendimento>();

	public ClienteRepo() {

	}

	public ClienteRepo(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> buscarTodos() {

		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.addOrder(Order.desc("nome"));
		List<Cliente> objeto = q.list();

		return objeto;

	}

	public Cliente buscaPorId(int id) {

		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.eq("id.idCliente", id));
		List<Cliente> objeto = q.list();
		return objeto.get(0);

	}

	public List<Cliente> buscarNome() {

		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.like("nome", cliente.getNome(), MatchMode.ANYWHERE));
		q.addOrder(Order.desc("nome"));
		List<Cliente> objeto = q.list();

		session.close();

		return objeto;

	}

	public List<Cliente> BuscaNomeLike(BeanMedio cliente) {
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.like("nome", cliente.getTexto(), MatchMode.ANYWHERE));
		q.addOrder(Order.asc("nome"));

		List<Cliente> objeto = q.list();

		return objeto;

	}

	public List<Cliente> BuscarNovoApto(String apto) {
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.eq("nome", apto));
		List<Cliente> objeto = q.list();

		return objeto;

	}

	public List<Cliente> BuscarNovoAptoPorMac(int id) {
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.eq("idAtendimento", id));
		List<Cliente> objeto = q.list();

		return objeto;

	}

	public List<Cliente> BuscarTodosMacHabilitados() {
		// Cria sessao com o banco
		Session session = HibernateUtil.getSession();
		// Cria query de consulta
		Query q = session
				.createQuery("from Cliente u where u.idAtendimento > :idAtendimento");
		// Seta o parametro pelo :
		q.setParameter("idAtendimento", 0);
		// joga o resultado da pesquisa para uma lista
		List<Cliente> objeto = q.list();

		return objeto;

	}

	public Cliente removerMac() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
			cliente.setFone(null);
			cliente.setIdAtendimento(0);
			cliente.setStatus("Sem comunicação");
			cliente.setRenovacao("LayoutNovo/Imagem/neutro.png");
			cliente.setRetorno("LayoutNovo/Imagem/neutro.png");
			session.merge(cliente);
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}

	public Cliente atualizarStatus() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {

			session.merge(cliente);
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}

	public void init() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		String pagina = null;
		// Em modo thread não foi possivel gerar facesContext
		// HttpSession session2 = (HttpSession)
		// FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		// Usuario usu = (Usuario) session2.getAttribute("currentUser");

		UsuarioRepo usuarioRepo = new UsuarioRepo();
		atendimentoRepo = new AtendimentoRepo();
		Usuario usu = new Usuario();
		usu = usuarioRepo.buscarId(1).get(0);

		cliente.setStatus("Sem comunicação");
		cliente.setFone2("LayoutNovo/Imagem/AUTO.png");
		cliente.setRetorno("LayoutNovo/Imagem/neutro.png");
		cliente.setRenovacao("LayoutNovo/Imagem/neutro.png");

		clienteId.setUsuarioIdUsuario(usu.getIdUsuario());
		cliente.setUsuario(usu);
		cliente.setId(clienteId);

		try {

			session.merge(cliente);
			t.commit();

			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes
			cliente = new Cliente();

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
			System.out.println("Energia/Erro/" + e.getMessage());
		} finally {
			// fecha conexao
			session.close();

		}

	}

	public String salvar() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();
		boolean edit = true;
		String pagina = null;
		// Em modo thread não foi possivel gerar facesContext
		// HttpSession session2 = (HttpSession)
		// FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		// Usuario usu = (Usuario) session2.getAttribute("currentUser");

		UsuarioRepo usuarioRepo = new UsuarioRepo();
		atendimentoRepo = new AtendimentoRepo();
		Usuario usu = new Usuario();
		usu = usuarioRepo.buscarId(1).get(0);

		if (!atendimentoRepo.getForId(cliente.getIdAtendimento()).isEmpty()) {
			cliente.setFone(atendimentoRepo
					.getForId(cliente.getIdAtendimento()).get(0).getMac());
			//cliente.setStatus("Operando");
		}

		try {
			cliente.getId().equals(new ClienteId());
		} catch (Exception e) {
			clienteId.setUsuarioIdUsuario(usu.getIdUsuario());
			cliente.setUsuario(usu);
			cliente.setId(clienteId);
			edit = false;
		}

		try {

			session.merge(cliente);
			t.commit();

			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes
			cliente = new Cliente();

			if (edit) {
				pagina = "edit";
			}

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
			System.out.println("Energia/Erro/" + e.getMessage());
		} finally {
			// fecha conexao
			session.close();

		}
		return pagina;

	}
	
	public Cliente update() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
	
			session.createSQLQuery("UPDATE `energia`.`cliente` SET `cpf`='"+cliente.getCpf()+"' WHERE `idCliente`="+cliente.getId().getIdCliente()+" and`Usuario_idUsuario`=1").executeUpdate();
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}
	
	public Cliente updateControleManual() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
	
			session.createSQLQuery("UPDATE `energia`.`cliente` SET `retorno`='"+cliente.getRetorno()+"', `renovacao`='"+cliente.getRenovacao()+"' WHERE `idCliente`="+cliente.getId().getIdCliente()+" and`Usuario_idUsuario`=1").executeUpdate();
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}
	
	public Cliente updateStatus() throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
	
			session.createSQLQuery("UPDATE `energia`.`cliente` SET `status`='"+cliente.getStatus()+"' WHERE `idCliente`="+cliente.getId().getIdCliente()+" and`Usuario_idUsuario`=1").executeUpdate();
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}
	
	public Cliente updateCampo(String campo, String valor) throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();

		try {
	
			session.createSQLQuery("UPDATE `energia`.`cliente` SET `"+campo+"`='"+valor+"' WHERE `idCliente`="+cliente.getId().getIdCliente()+" and`Usuario_idUsuario`=1").executeUpdate();
			t.commit();
			// SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			// SessionUtil.addErrorMessage("OperacaoFracasso");
		} finally {
			// fecha conexao
			session.close();

		}
		return cliente;

	}

	public List<Cliente> BuscarAptoEMac(BeanMedio cliente) {
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.like("nome", cliente.getTexto(), MatchMode.ANYWHERE));
		q.addOrder(Order.asc("nome"));
		List<Cliente> objeto = q.list();
		atendimentoRepo = new AtendimentoRepo();
		for (Cliente clienteComMac : objeto) {
			try {
				/*
				 * if (clienteComMac.getIdAtendimento() == 0) {
				 * clienteComMac.setFone2("LayoutNovo/Imagem/neutro.png");
				 * clienteComMac.setRenovacao("LayoutNovo/Imagem/neutro.png");
				 * clienteComMac.setRetorno("LayoutNovo/Imagem/neutro.png");
				 * 
				 * }
				 */
			} catch (Exception e) {

			}

		}

		return objeto;

	}

	public void AtualizarStatusApto(Atendimento mac) {
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Cliente.class);
		q.add(Restrictions.eq("idAtendimento", mac.getId().getIdAtendimento()));
		List<Cliente> objeto = q.list();
		if (!objeto.isEmpty()) {
			atendimentoRepo = new AtendimentoRepo();
			Date horaAtual = new Date();
			Date ultimaComunicacao;
			Cliente clienteComMac = objeto.get(0);// olhar aqui
			ultimaComunicacao = mac.getData();
			Calendar cal = Calendar.getInstance();
			cal.setTime(ultimaComunicacao);
			cal.add(Calendar.MINUTE, 1);
			ultimaComunicacao = cal.getTime();
			if (ultimaComunicacao.after(horaAtual)) {
				clienteComMac.setStatus("Operando");
			} else {
				clienteComMac.setStatus("Sem comunicação");
			}

			this.cliente = clienteComMac;
			updateStatus();
		}
	}

}
