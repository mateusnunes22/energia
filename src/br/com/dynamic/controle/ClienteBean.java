package br.com.dynamic.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.estrutura.Periodo;
import br.com.dynamic.estrutura.ThreadBroadcastForcada;
import br.com.dynamic.estrutura.ConsultaExterna;
import br.com.dynamic.estrutura.HttpURL;
import br.com.dynamic.estrutura.ThreadBroadcastForcadaUnica;
import br.com.dynamic.http.util.HttpServer;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConsultaExternaRepo;
import br.com.dynamic.scheduler.Main;

public class ClienteBean {

	private Cliente cliente = new Cliente();
	private BeanMedio clienteList = new BeanMedio();
	public List<Cliente> listaNomeLike = new ArrayList<Cliente>();
	public List<SelectItem> Combobox = new ArrayList<SelectItem>();
	private UIData objTabela;
	ClienteRepo clienteRepo;
	AtendimentoRepo atendimentoRepo;
	List<Atendimento> atendimento;
	HttpURL clientHttp;
	ConsultaExterna consultaExterna = new ConsultaExterna();
	ConsultaExternaRepo consultaExternaRepo = new ConsultaExternaRepo();

	public String atualizar() throws IllegalStateException {

		Periodo.delay(100);
		return null;

	}

	public String logar() throws IllegalStateException {

		return "logado";

	}

	public String buscarWEC() throws IllegalStateException {

		ThreadBroadcastForcadaUnica broadcast = new ThreadBroadcastForcadaUnica();
		broadcast.start();

		return null;

	}

	public String salvar() throws IllegalStateException {

		clienteRepo = new ClienteRepo(cliente);

		clienteList.setPagina(clienteRepo.salvar());

		cliente = new Cliente();

		Periodo.delay(300);

		return clienteList.getPagina();
	}

	public String editar() throws IllegalStateException {

		cliente = (Cliente) objTabela.getRowData();

		return "edit";
	}

	public String modoOperacao() throws IllegalStateException {

		cliente = (Cliente) objTabela.getRowData();
		if (cliente.getStatus().equalsIgnoreCase("Operando")) {
			if ("LayoutNovo/Imagem/AUTO.png".equalsIgnoreCase(cliente
					.getFone2())) {
				cliente.setFone2("LayoutNovo/Imagem/MANUAL.png");
			} else {
				cliente.setFone2("LayoutNovo/Imagem/AUTO.png");
			}

			clienteRepo = new ClienteRepo(cliente);
			clienteRepo.updateCampo("fone2", cliente.getFone2());
			Periodo.delay(400);
		}
		return null;
	}

	public String alterarLuz() throws IllegalStateException {

		cliente = (Cliente) objTabela.getRowData();
		if (cliente.getStatus().equalsIgnoreCase("Operando")) {
			cliente = (Cliente) objTabela.getRowData();
			clientHttp = new HttpURL();
			atendimentoRepo = new AtendimentoRepo();
			atendimento = new ArrayList<Atendimento>();
			atendimento = atendimentoRepo.getForId(cliente.getIdAtendimento());

			if (!cliente.getFone2().equalsIgnoreCase(
					"LayoutNovo/Imagem/AUTO.png")) {

				boolean RLL = "LayoutNovo/Imagem/ligado.png"
						.equalsIgnoreCase(cliente.getRenovacao());
				if ("LayoutNovo/Imagem/ligado.png".equalsIgnoreCase(cliente
						.getRetorno())) {
					cliente.setRetorno("LayoutNovo/Imagem/desligado.png");
					if (!atendimento.isEmpty()) {
						/*if (RLL) {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LlZd");
						} else {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LdZd");
						}*/
					}
				} else {
					cliente.setRetorno("LayoutNovo/Imagem/ligado.png");
					if (!atendimento.isEmpty()) {
						/*if (RLL) {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LlZl");
						} else {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LdZl");
						}*/
					}
				}

				clienteRepo = new ClienteRepo(cliente);
				clienteRepo.updateCampo("retorno", cliente.getRetorno());
				Periodo.delay(400);
			}
		}
		return null;
	}

	public String alterarLocacao() throws IllegalStateException {

		cliente = (Cliente) objTabela.getRowData();
		if (cliente.getStatus().equalsIgnoreCase("Operando")) {
			cliente = (Cliente) objTabela.getRowData();
			clientHttp = new HttpURL();
			atendimentoRepo = new AtendimentoRepo();
			atendimento = new ArrayList<Atendimento>();
			atendimento = atendimentoRepo.getForId(cliente.getIdAtendimento());

			if (!cliente.getFone2().equalsIgnoreCase(
					"LayoutNovo/Imagem/AUTO.png")) {

				boolean RZL = "LayoutNovo/Imagem/ligado.png"
						.equalsIgnoreCase(cliente.getRetorno());
				if ("LayoutNovo/Imagem/ligado.png".equalsIgnoreCase(cliente
						.getRenovacao())) {
					cliente.setRenovacao("LayoutNovo/Imagem/desligado.png");
					if (!atendimento.isEmpty()) {
						/*if (RZL) {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LdZl");
						} else {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LdZd");
						}*/
					}
				} else {
					cliente.setRenovacao("LayoutNovo/Imagem/ligado.png");
					if (!atendimento.isEmpty()) {
						/*if (RZL) {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LlZl");
						} else {
							clientHttp
									.verificarIpEMac("http://"
											+ atendimento.get(0).getIp()
											+ ":8091/LlZd");
						}*/
					}
				}

				clienteRepo = new ClienteRepo(cliente);
				clienteRepo.updateCampo("renovacao", cliente.getRenovacao());
				Periodo.delay(400);
			}
		}
		return null;
	}

	public String novo() throws IllegalStateException {

		cliente = new Cliente();

		return "edit";
	}

	public List<Cliente> getListaNomeLike() {

		clienteRepo = new ClienteRepo();

		listaNomeLike = clienteRepo.BuscarAptoEMac(clienteList);

		clienteList.setSize(listaNomeLike.size());

		return listaNomeLike;
	}

	public String removerMac() throws IllegalStateException {

		cliente = (Cliente) objTabela.getRowData();

		clienteRepo = new ClienteRepo(cliente);

		clienteRepo.removerMac();

		Periodo.delay(400);

		return null;
	}

	public List<SelectItem> getClienteNome() {
		clienteRepo = new ClienteRepo();
		Combobox.clear();
		List<Cliente> clientes = clienteRepo.buscarTodos();
		for (Cliente c : clientes) {
			SelectItem s = new SelectItem();
			s.setValue(c.getId().getIdCliente());
			s.setLabel(c.getNome());
			Combobox.add(s);
		}
		return Combobox;
	}

	public void setListaNomeLike(List<Cliente> listaNomeLike) {
		this.listaNomeLike = listaNomeLike;
	}

	public UIData getObjTabela() {
		return objTabela;
	}

	public void setObjTabela(UIData objTabela) {
		this.objTabela = objTabela;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClienteList(BeanMedio clienteList) {
		this.clienteList = clienteList;
	}

	public BeanMedio getClienteList() {
		return clienteList;
	}

	public List<SelectItem> getCombobox() {
		return Combobox;
	}

	public void setCombobox(List<SelectItem> combobox) {
		Combobox = combobox;
	}

}
