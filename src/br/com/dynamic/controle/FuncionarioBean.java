package br.com.dynamic.controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import javax.faces.model.SelectItem;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.estrutura.BeanMedio;
import br.com.dynamic.estrutura.ConexaoUtil;
import br.com.dynamic.repo.ConexaoRepo;
import br.com.dynamic.repo.FuncionarioRepo;

public class FuncionarioBean {
	
	
	private Funcionario funcionario = new Funcionario();
	private ConexaoUtil conexao = new ConexaoUtil();
	private BeanMedio funcionarioList = new BeanMedio();
	public List<Funcionario> listaNomeLike =  new ArrayList<Funcionario>();
	public List<SelectItem> Combobox =  new ArrayList<SelectItem>();
	private UIData objTabela;
	FuncionarioRepo funcionarioRepo;
	ConexaoRepo conexaoRepo;
	
	
	public String atualizar() throws IllegalStateException {
		
		return null;
	}
	
	public String salvarConexao() throws IllegalStateException {
		
		conexaoRepo = new ConexaoRepo(conexao);	
		conexaoRepo.salvar();
		conexao = new ConexaoUtil();
		return "home";	

	}
	
	
	public String salvar() throws IllegalStateException {
		
		funcionarioRepo = new FuncionarioRepo(funcionario);	
		
		funcionarioList.setPagina(funcionarioRepo.salvar());
		
		funcionario = new Funcionario(); 
		
		return funcionarioList.getPagina();	

	}
	
	public String editar() throws IllegalStateException {
		
		funcionario = (Funcionario) objTabela.getRowData();
		
		return "edit";
	}
	
	public String novo() throws IllegalStateException {
		
		funcionario = new Funcionario();
		
		return "edit";
	}	
	
	public List<Funcionario> getListaNomeLike() {
		
		funcionarioRepo = new FuncionarioRepo();
		
		listaNomeLike = funcionarioRepo.BuscaNomeLike(funcionarioList);
		
		funcionarioList.setSize(listaNomeLike.size());
		
		return listaNomeLike;
	}
	
	public List<SelectItem> getFuncionarioNome(){
		
	     funcionarioRepo = new FuncionarioRepo();
	  
	     Combobox.clear();
	     
	     List<Funcionario> funcionarios = funcionarioRepo.buscarTodos();  
	     
	        for  (Funcionario c : funcionarios){    
	              SelectItem  s = new SelectItem();    
	              s.setValue(c.getId().getIdFuncionario());    
	              s.setLabel( c.getNome());
	             Combobox.add(s);   
	       }
	        
	       return Combobox;  
	     
}

	public List<SelectItem> getCombobox() {
		return Combobox;
	}

	public void setCombobox(List<SelectItem> combobox) {
		Combobox = combobox;
	}

	public void setListaNomeLike(List<Funcionario> listaNomeLike) {
		this.listaNomeLike = listaNomeLike;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public UIData getObjTabela() {
		return objTabela;
	}


	public void setObjTabela(UIData objTabela) {
		this.objTabela = objTabela;
	}
	
	public void setFuncionarioList(BeanMedio funcionarioList) {
		this.funcionarioList = funcionarioList;
	}
	
	public BeanMedio getFuncionarioList() {
		return funcionarioList;
	}


	public void setConexao(ConexaoUtil conexao) {
		this.conexao = conexao;
	}


	public ConexaoUtil getConexao() {
		return conexao;
	}
	
	

}
