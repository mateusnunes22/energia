package br.com.dynamic.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.dynamic.repo.ConexaoRepo;


@Entity
@Table(name = "conexao", catalog = "energia")
public class Conexao implements java.io.Serializable {

	private Integer idConexao;
	private String ip;
	private String porta;
	private String baseDeDados;
	private String ssid;
	private String senha;
	
	
	public Conexao() {
	}


	public Conexao(Integer idConexao,String ip, String porta, String baseDeDados, String ssid, String senha) {
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idConexao", unique = true, nullable = false)
	public Integer getIdConexao() {
		return idConexao;
	}
	
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "ip", nullable = false, length = 40)
	public String getIp() {
		return ip;
	}


	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	@Column(name = "ssid", nullable = false, length = 40)
	public String getSsid() {
		return ssid;
	}

	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "senha", nullable = false, length = 40)
	public String getSenha() {
		return senha;
	}


	public void setPorta(String porta) {
		this.porta = porta;
	}

	@Column(name = "porta", nullable = false, length = 10)
	public String getPorta() {
		return porta;
	}


	public void setBaseDeDados(String baseDeDados) {
		this.baseDeDados = baseDeDados;
	}

	@Column(name = "baseDeDados", nullable = false, length = 20)
	public String getBaseDeDados() {
		return baseDeDados;
	}
	
	
	
	
}
