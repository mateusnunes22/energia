package br.com.dynamic.estrutura;

import java.util.ArrayList;
import java.util.List;

import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConexaoRepo;



public class ConsultaExterna{

	private String nDoQuarto;
	private String statusDoQuarto;
	
	ClienteRepo clienteRepo;
	
	public ConsultaExterna() {
		
		this.nDoQuarto = "";
		this.statusDoQuarto = "";
		
	}
	
	public ConsultaExterna(String nDoQuarto, String statusDoQuarto) {
		super();
		this.nDoQuarto = nDoQuarto;
		this.statusDoQuarto = statusDoQuarto;
	}
	
	public static String parametros(List<Cliente> clientes){
		
		String sql = "";
		if(!clientes.isEmpty())
		{
			for(int i = 0;i<clientes.size();i++)
			{
				if(i==0){
				sql = clientes.get(0).getNome();
				}
				else{
					sql += ","+clientes.get(0).getNome();
				}
				
			}
		}
		
		return sql;
	}
	
public void processamento(List<Funcionario> funcionarios) {
		
		Cliente novoApto = new Cliente();
		clienteRepo = new ClienteRepo();
		
		
		for(Funcionario aptoExterno : funcionarios)
		{	
			List<Cliente> apto = clienteRepo.BuscarNovoApto(aptoExterno.getNome());
			System.out.println(!apto.get(0).getCpf().equalsIgnoreCase(aptoExterno.getEspecialidade()));
			System.out.println(aptoExterno.getEspecialidade());
			System.out.println(apto.get(0).getCpf());
			if(apto.isEmpty())
			{
				novoApto.setFone(" ");
				novoApto.setNome(aptoExterno.getNome());
				novoApto.setCpf(aptoExterno.getEspecialidade());
				clienteRepo = new ClienteRepo(novoApto);
				clienteRepo.salvar();
				novoApto = new Cliente();
			}
			else if(!apto.get(0).getCpf().equalsIgnoreCase(aptoExterno.getEspecialidade()))
			{
				novoApto = apto.get(0);
				novoApto.setCpf(aptoExterno.getEspecialidade());
				enviarNovoStatus(novoApto);
				clienteRepo = new ClienteRepo(novoApto);
				clienteRepo.salvar();
			}
			
			
		}
		
		
	}
	
	
	public void enviarNovoStatus(Cliente apto) {
		
		HttpURL clientHttp = new HttpURL();
		String url = "";
		
		if("L".equalsIgnoreCase(apto.getCpf())){
			System.out.println("COnsegui!!!LLLLLLLLLLL");
			url = "http://" + apto.getFone() + "/*1!@1";
			clientHttp.acessarURL(url);
			System.out.println(url);
		}
		else if("E".equalsIgnoreCase(apto.getCpf())){
			System.out.println("COnsegui!!!EEEEEEEEEEEEEEEE");
			url = "http://" + apto.getFone() + "/*1!@1";
			clientHttp.acessarURL(url);
			System.out.println(url);
		}
       else if("D".equalsIgnoreCase(apto.getCpf())){
       	System.out.println("COnsegui!!!DDDDDDDDDDDDDDDDDD");
       	url = "http://" + apto.getFone() + "/*0!@0";
       	clientHttp.acessarURL(url);
			System.out.println(url);
		}
       else if("M".equalsIgnoreCase(apto.getCpf())){
       	System.out.println("COnsegui!!!MMMMMMMMMMMMMMMMMMMMM");
       	url = "http://" + apto.getFone() + "/*1!@0";
       	clientHttp.acessarURL(url);
			System.out.println(url);
       }
		
	}
	
	public String getnDoQuarto() {
		return nDoQuarto;
	}
	public void setnDoQuarto(String nDoQuarto) {
		this.nDoQuarto = nDoQuarto;
	}
	public String getStatusDoQuarto() {
		return statusDoQuarto;
	}
	public void setStatusDoQuarto(String statusDoQuarto) {
		this.statusDoQuarto = statusDoQuarto;
	}
	
	
	
	
	
	
}
