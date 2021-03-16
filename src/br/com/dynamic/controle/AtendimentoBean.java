package br.com.dynamic.controle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.AtendimentoRepo;

public class AtendimentoBean {
	
	
	private Cliente cliente = new Cliente();
	private BeanMedio atendimentoList = new BeanMedio();
	public List<Atendimento> lista =  new ArrayList<Atendimento>();
	public List<Cliente> informacao =  new ArrayList<Cliente>();
	public List<Atendimento> listaForgId =  new ArrayList<Atendimento>();
	public List<SelectItem> Combobox =  new ArrayList<SelectItem>();
	private UIData objTabela;
	private AtendimentoRepo atendimentoRepo;
	private ClienteRepo clienteRepo;
	
	public String salvar() throws IllegalStateException {
		clienteRepo = new ClienteRepo(cliente);
		atendimentoList = new BeanMedio();
		//atendimentoList.getPagina()
		return "home";	
	}	
	
	public String atendimentoInicio() throws IllegalStateException {
		
		cliente = (Cliente) objTabela.getRowData();
		
		return "atendimento";
		
	}	
	
	public List<Atendimento> getLista() {
		lista = atendimentoRepo.getLista();
		atendimentoList.setSize(lista.size());
		return lista;
	}

	public void setListaForgId(List<Atendimento> listaForgId) {
		this.listaForgId = listaForgId;
	}

	public List<Cliente> getInformacao() {
		informacao.clear();
		informacao.add(cliente);
		return informacao;
	}
	
	public List<SelectItem> getMac() {
		atendimentoRepo = new AtendimentoRepo();
		clienteRepo = new ClienteRepo();
		
		Combobox.clear();
		
		List<Atendimento> clientes = atendimentoRepo.getLista();
		List<Atendimento> remocao = new ArrayList<Atendimento>();
		for(Atendimento cliente : clientes)
		{
			if(!clienteRepo.BuscarNovoAptoPorMac(cliente.getId().getIdAtendimento()).isEmpty())
			{
				remocao.add(cliente);
			}
			
		}
		clientes.removeAll(remocao);
		for (Atendimento c : clientes) {
			SelectItem s = new SelectItem();
			s.setValue(c.getId().getIdAtendimento());
			s.setLabel(c.getMac());
			Combobox.add(s);
		}
		return Combobox;
	}

	public void setInformacao(List<Cliente> informacao) {
		this.informacao = informacao;
	}

	public void setLista(List<Atendimento> lista) {
		this.lista = lista;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<SelectItem> getCombobox() {
		return Combobox;
	}

	public void setCombobox(List<SelectItem> combobox) {
		Combobox = combobox;
	}

	public void setObjTabela(UIData objTabela) {
		this.objTabela = objTabela;
	}

	public UIData getObjTabela() {
		return objTabela;
	}

	public BeanMedio getAtendimentoList() {
		return atendimentoList;
	}

	public void setAtendimentoList(BeanMedio atendimentoList) {
		this.atendimentoList = atendimentoList;
	}
	
	


}
