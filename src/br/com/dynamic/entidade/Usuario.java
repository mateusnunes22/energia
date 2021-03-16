package br.com.dynamic.entidade;

// Generated 08/10/2014 05:47:29 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.dynamic.login.Perfil;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", catalog = "energia", uniqueConstraints = @UniqueConstraint(columnNames = "nomeUsuario"))
public class Usuario implements java.io.Serializable {

	private Integer idUsuario;
	private String nomeUsuario;
	private String email;
	private String senha;
	private String perfil;
	private Set clientes = new HashSet(0);
	private Set atendimentos = new HashSet(0);
	private Set funcionarios = new HashSet(0);

	public Usuario() {
	}

	public Usuario(String nomeUsuario, String email, String senha, String perfil) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
	}

	public Usuario(String nomeUsuario, String email, String senha,
			String perfil, Set clientes, Set atendimentos, Set funcionarios) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
		this.clientes = clientes;
		this.atendimentos = atendimentos;
		this.funcionarios = funcionarios;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUsuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "nomeUsuario", unique = true, nullable = false, length = 80)
	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Column(name = "email", nullable = false, length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "senha", nullable = false, length = 60)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "perfil", nullable = false, length = 15)
	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set getClientes() {
		return this.clientes;
	}

	public void setClientes(Set clientes) {
		this.clientes = clientes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(Set atendimentos) {
		this.atendimentos = atendimentos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(Set funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public boolean temAcesso(String pagina, String perf) {


    	Perfil p = new Perfil(perf);



    	return p.temAcesso(pagina);
    }

}
