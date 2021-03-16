package br.com.dynamic.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Ordem;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.OrdemRepo;

public class OrdemBean {
	
	
	private BeanMedio ordemList = new BeanMedio();
	public List<Ordem> lista =  new ArrayList<Ordem>();
	OrdemRepo ordemRepo;
	public List<SelectItem> clientes =  new ArrayList<SelectItem>();
	private ClienteRepo clienteRepo;
	
	public String atualizar() throws IllegalStateException {
		
		return null;
					
	}
	
	public String salvar() throws IllegalStateException {
		
		ordemRepo = new OrdemRepo();	
		
		ordemList.setPagina(ordemRepo.salvar(ordemList)); 
		
		ordemList = new BeanMedio(ordemList.getData(), ordemList.getPagina());
		
		return ordemList.getPagina();	
	}

	public void setOrdemList(BeanMedio ordemList) {
		this.ordemList = ordemList;
	}

	public BeanMedio getOrdemList() {
		return ordemList;
	}

	public List<Ordem> getLista() {
		
		ordemRepo = new OrdemRepo();
		
		lista = ordemRepo.buscarLista(ordemList);
		
		ordemList.setSize(lista.size());
		
		return lista;
	}

	public void setLista(List<Ordem> lista) {
		this.lista = lista;
	}
	
	/*public void countryLocaleCodeChanged(ValueChangeEvent e){
		ordemList.setNumero3(2);
	}
	
	public void mudarItem(ValueChangeEvent e){
		ordemList.setNumero3(2);
	}*/
	
	public List<SelectItem> getClienteNome(){

		clienteRepo = new ClienteRepo();

		clientes.clear();

		List<Cliente> cliente = clienteRepo.buscarNome();  

		/*if(ordemList.getNumero3()==1){

			SelectItem  s2 = new SelectItem();    
			s2.setValue(0);    
			s2.setLabel("(Selecione)");
			clientes.add(s2);

			ordemList.setNumero3(0);
			ordemList.setNumero2(0);

		}
		else{
			SelectItem  s2 = new SelectItem();    
			s2.setValue(0);    
			s2.setLabel("(Selecione)");
			clientes.add(s2);    

			for  (Cliente c : cliente){    
				SelectItem  s = new SelectItem();    
				s.setValue(c.getId().getIdCliente());    
				s.setLabel(c.getNome());
				clientes.add(s);    
			}	 
		}

		if(ordemList.getNumero2()!=0){
			clienteRepo = new ClienteRepo();
			//ordemList.setValor(clienteRepo.buscaPorId(ordemList.getNumero2()).getId());
		}*/

		return clientes;  
	}

}
