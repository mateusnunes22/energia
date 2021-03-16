package br.com.dynamic.repo;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.util.HibernateUtil;
 
public class ConsultaExternaRepo {

 	   public List<Funcionario> buscarTodos(){
			
			Session session = HibernateUtil.getSession();
			Criteria q = session.createCriteria(Funcionario.class);
			q.addOrder(Order.desc("nome"));
			List<Funcionario> objeto = q.list();
			session.close();
			return objeto;
			
		}
 
}