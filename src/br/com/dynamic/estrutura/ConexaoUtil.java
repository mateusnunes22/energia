package br.com.dynamic.estrutura;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.repo.ConexaoRepo;


public class ConexaoUtil {

	private Integer idConexao;
	private String ip;
	private String porta;
	private String baseDeDados;
	private String ssid;
	private String senha;
	
	
	public ConexaoUtil() {
		ConexaoRepo conexaoRepo = new ConexaoRepo();	
		List<Conexao> conexaoUnica = new ArrayList<Conexao>();
		conexaoUnica = conexaoRepo.buscarTodos();
		if(!conexaoUnica.isEmpty())
		{
			Conexao init = conexaoUnica.get(0);
			this.setIdConexao(init.getIdConexao());
			this.setIp(init.getIp());
			this.setPorta(init.getPorta());
			this.setBaseDeDados(init.getBaseDeDados());
			this.setSsid(init.getSsid());
			this.setSenha(init.getSenha());
		}
	}


	public ConexaoUtil(Integer idConexao,String ip, String porta, String baseDeDados, String ssid, String senha) {
		this.setIdConexao(idConexao);
		this.setIp(ip);
		this.setPorta(porta);
		this.setBaseDeDados(baseDeDados);
		this.setSsid(ssid);
		this.setSenha(senha);
	}
	
	public void setIdConexao(Integer idConexao) {
		this.idConexao = idConexao;
	}


	public Integer getIdConexao() {
		return idConexao;
	}
	
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	public String getIp() {
		return ip;
	}


	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	
	public String getSsid() {
		return ssid;
	}

	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getSenha() {
		return senha;
	}


	public void setPorta(String porta) {
		this.porta = porta;
	}

	
	public String getPorta() {
		return porta;
	}


	public void setBaseDeDados(String baseDeDados) {
		this.baseDeDados = baseDeDados;
	}

	
	public String getBaseDeDados() {
		return baseDeDados;
	}
	
	
	
	
}
