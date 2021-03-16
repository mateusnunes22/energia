package br.com.dynamic.entidade;

// Generated 08/10/2014 05:47:29 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Atendimento generated by hbm2java
 */
@Entity
@Table(name = "atendimento", catalog = "energia", uniqueConstraints = @UniqueConstraint(columnNames = "mac"))
public class Atendimento implements java.io.Serializable {

	private AtendimentoId id;
	private Usuario usuario;
	private Date data;
	private String ip;
	private String mac;
	private Float pagamento;

	public Atendimento() {
	}

	public Atendimento(AtendimentoId id, Usuario usuario,
			Date data, String mac) {
		this.id = id;
		this.usuario = usuario;
		this.data = data;
		this.mac = mac;
	}

	public Atendimento(AtendimentoId id, Usuario usuario,
			Date data, String ip, String mac, Float pagamento) {
		this.id = id;
		this.usuario = usuario;
		this.data = data;
		this.ip = ip;
		this.mac = mac;
		this.pagamento = pagamento;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "idAtendimento", column = @Column(name = "idAtendimento", nullable = false)),
			@AttributeOverride(name = "usuarioIdUsuario", column = @Column(name = "Usuario_idUsuario", nullable = false)) })
	public AtendimentoId getId() {
		return this.id;
	}

	public void setId(AtendimentoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Usuario_idUsuario", nullable = false, insertable = false, updatable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false, length = 19)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "ip", nullable = false, length = 80)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "mac", nullable = false, length = 80)
	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Column(name = "pagamento", precision = 12, scale = 0)
	public Float getPagamento() {
		return this.pagamento;
	}

	public void setPagamento(Float pagamento) {
		this.pagamento = pagamento;
	}

}
