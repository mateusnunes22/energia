package br.com.dynamic.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import br.com.dynamic.entidade.Ordem;
import br.com.dynamic.entidade.OrdemId;
import br.com.dynamic.entidade.Usuario;
import br.com.dynamic.estrutura.Bean;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.util.HibernateUtil;
import br.com.dynamic.util.SessionUtil;


public class OrdemRepo {
	
	
	private Ordem ordem = new Ordem();
	OrdemId ordemId = new OrdemId();
	
	
	public OrdemRepo() {
		
	}
	
	public OrdemRepo(Ordem ordem) {
		this.ordem = ordem;
	}

	public List<Ordem> buscarLista(BeanMedio ordemList){
		
		int contador = 0;
		List<Ordem> listaOrdem = new ArrayList<Ordem>();
		String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(new Date());
		data = data.substring(0, 11);
		data = data + "00:00:00.0";
		Date dataInicial = new Date();
		
		try {
			dataInicial = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Session session = HibernateUtil.getSession();
		Criteria q = session.createCriteria(Ordem.class);
		q.add(Restrictions.between("data", dataInicial,new Date()));
		q.addOrder(Order.asc("id.idOrdem"));
		List<Ordem> objeto = q.list();
		
		for( Ordem ordemObjeto : objeto )
		{
			contador++;
			OrdemId ordemIdObjeto = new OrdemId();
			ordemIdObjeto.setIdOrdem(contador);
			ordemObjeto.setId(ordemIdObjeto);
			listaOrdem.add(ordemObjeto);
		}
		
		return objeto;
		
	}

	public String salvar(BeanMedio ordemList) throws IllegalStateException {
		// cria sessao com banco e transacao
		Session session = HibernateUtil.getSession();
		Transaction t = session.beginTransaction();	
		ClienteRepo clienteRepo = new ClienteRepo();
		FuncionarioRepo funcionarioRepo = new FuncionarioRepo();
		
		ordem.setData(ordemList.getData().getDataInicial());
		ordem.setCliente(clienteRepo.buscaPorId(ordemList.getNumero()));
		ordem.setFuncionario(funcionarioRepo.buscaPorId(ordemList.getNumero2()));
		ordemId.setClienteIdCliente(ordem.getCliente().getId().getIdCliente());
		ordemId.setClienteUsuarioIdUsuario(ordem.getCliente().getUsuario().getIdUsuario());
		ordemId.setFuncionarioIdFuncionario(ordem.getFuncionario().getId().getIdFuncionario());
		ordemId.setFuncionarioUsuarioIdUsuario(ordem.getFuncionario().getUsuario().getIdUsuario());
		ordem.setId(ordemId);
		ordem.setData(new Date(new Date().getTime()-17992800));
		
		try {
		
			session.merge(ordem);
			t.commit();
			SessionUtil.addSuccessMessage("OperacaoSucesso");
			// limpa objeto funcionario para novas insercoes
			

		} catch (Exception e) {
			// se algo errado ocorrer cancela a trasacao
			t.rollback();
			SessionUtil.addErrorMessage("OperacaoFracasso");
			System.out.println("Dentista/Erro/"+e.getMessage());
		}finally{
			//fecha conexao
			session.close();
			
		}
		return null;
					
	}
	

}
