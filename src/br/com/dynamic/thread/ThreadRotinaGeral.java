package br.com.dynamic.thread;

import java.util.ArrayList;
import java.util.List;
import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.estrutura.ConsultaExterna;
import br.com.dynamic.estrutura.ControleManual;
import br.com.dynamic.estrutura.HttpURL;
import br.com.dynamic.estrutura.OracleConnection;
import br.com.dynamic.estrutura.Periodo;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConexaoRepo;
import br.com.dynamic.repo.ConsultaExternaRepo;

/**
 * 
 * @author  Thread só com comando; rotina de recuperação de wec 5 min até login
 */
public class ThreadRotinaGeral extends Thread {

	OracleConnection conn;
	ConsultaExterna consultaExterna = new ConsultaExterna();
	ConsultaExternaRepo consultaExternaRepo = new ConsultaExternaRepo();
	ClienteRepo clienteRepo;
	AtendimentoRepo atendimentoRepo;
	ConexaoRepo conexaoRepo;

	public void processamento(ArrayList<ConsultaExterna> funcionarios,
			List<Cliente> atualizacaoRele) {

		Cliente novoApto = new Cliente();
		clienteRepo = new ClienteRepo();
		atendimentoRepo = new AtendimentoRepo();
		Atendimento atendimento = new Atendimento();

		ControleManual controleManual = new ControleManual();

		for (Cliente atualizacao : atualizacaoRele) {
			atualizacao = clienteRepo.buscaPorId(atualizacao.getId().getIdCliente());
			atendimento = atendimentoRepo.buscarForgId(
					atualizacao.getIdAtendimento()).get(0);
			if (atualizacao.getFone2().equalsIgnoreCase(
					"LayoutNovo/Imagem/AUTO.png")) {
				controleManual.atualizarStatus(atualizacao);
				enviarNovoStatus(atualizacao, atendimento);
			}
			else if (atualizacao.getFone2().equalsIgnoreCase(
			"LayoutNovo/Imagem/MANUAL.png")) {
				enviarNovoStatusManual(atualizacao, atendimento);
			}
			Periodo.delay(15);
		}
		
		clienteRepo = new ClienteRepo();

		for (ConsultaExterna aptoExterno : funcionarios) {
			List<Cliente> apto = clienteRepo.BuscarNovoApto(aptoExterno
					.getnDoQuarto());
			if (apto.isEmpty()) {
				novoApto.setFone(null);
				novoApto.setNome(aptoExterno.getnDoQuarto());
				novoApto.setCpf(aptoExterno.getStatusDoQuarto());
				clienteRepo = new ClienteRepo(novoApto);
				clienteRepo.init();
				novoApto = new Cliente();
			} else if (!apto.get(0).getCpf()
					.equalsIgnoreCase(aptoExterno.getStatusDoQuarto())) {
				System.out.println(aptoExterno.getnDoQuarto());
				System.out.println("novo");
				System.out.println(aptoExterno.getStatusDoQuarto());
				novoApto = apto.get(0);
				novoApto.setCpf(aptoExterno.getStatusDoQuarto());
				if (novoApto.getFone2().equalsIgnoreCase(
						"LayoutNovo/Imagem/AUTO.png")) {
					if (!atendimentoRepo.buscarForgId(
							novoApto.getIdAtendimento()).isEmpty()) {
						enviarNovoStatus(
								novoApto,
								atendimentoRepo.buscarForgId(
										novoApto.getIdAtendimento()).get(0));
					}
				}
				clienteRepo = new ClienteRepo(novoApto);
				clienteRepo.update();
				novoApto = new Cliente();
			}

		}

	}

	public void enviarNovoStatus(Cliente apto, Atendimento atendimento) {

		ThreadEnvioComando envioComando;
		String url = "";

		if ("L".equalsIgnoreCase(apto.getCpf())) { // locado

			url = "http://" + atendimento.getIp() + ":8091/LOCA";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("E".equalsIgnoreCase(apto.getCpf())) { // encerramento

			url = "http://" + atendimento.getIp() + ":8091/ENCE";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("D".equalsIgnoreCase(apto.getCpf())) {// disponivel

			url = "http://" + atendimento.getIp() + ":8091/DISP";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("M".equalsIgnoreCase(apto.getCpf())) { // manutencao

			url = "http://" + atendimento.getIp() + ":8091/MANU";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("R".equalsIgnoreCase(apto.getCpf())) { // reservado

			url = "http://" + atendimento.getIp() + ":8091/RESE";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("X".equalsIgnoreCase(apto.getCpf())) { // faxina

			url = "http://" + atendimento.getIp() + ":8091/FAXI";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("A".equalsIgnoreCase(apto.getCpf())) {// Aguardando

			url = "http://" + atendimento.getIp() + ":8091/SUJO";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("Z".equalsIgnoreCase(apto.getCpf())) { // limpeza

			url = "http://" + atendimento.getIp() + ":8091/LIMP";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("P".equalsIgnoreCase(apto.getCpf())) { // pernoite

			url = "http://" + atendimento.getIp() + ":8091/PERN";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("V".equalsIgnoreCase(apto.getCpf())) { // revisao

			url = "http://" + atendimento.getIp() + ":8091/REVI";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("I".equalsIgnoreCase(apto.getCpf())) { // diaria

			url = "http://" + atendimento.getIp() + ":8091/DIAR";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("C".equalsIgnoreCase(apto.getCpf())) { // reconferencia

			url = "http://" + atendimento.getIp() + ":8091/RECO";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if ("F".equalsIgnoreCase(apto.getCpf())) {

			url = "http://" + atendimento.getIp() + ":8091/LOCF";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else
		{
			envioComando = new ThreadEnvioComando("");
		}
		
		int timeout=0;
		for(;;)
		{
			timeout++;
			Periodo.delay(25);
			if(envioComando.getState().equals(Thread.State.TERMINATED)){
				break;
				
			}
			
			if(timeout>22){
				envioComando.interrupt();
				System.out.println("Sem resposta no IP: " + atendimento.getIp() );
				clienteRepo = new ClienteRepo();
				clienteRepo.AtualizarStatusApto(atendimento);
				break;
			}
		}
		System.out.println(url);

	}
	
	public void enviarNovoStatusManual(Cliente apto, Atendimento atendimento) {

		ThreadEnvioComando envioComando;
		String url = "";
		boolean RZL = "LayoutNovo/Imagem/ligado.png".equalsIgnoreCase(apto.getRetorno());
		boolean RLL = "LayoutNovo/Imagem/ligado.png".equalsIgnoreCase(apto.getRenovacao());

		if (RZL && RLL) { 

			url = "http://" + atendimento.getIp() + ":8091/LlZl";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if (!RZL && RLL) { 

			url = "http://" + atendimento.getIp() + ":8091/LlZd";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if (RZL && !RLL) {

			url = "http://" + atendimento.getIp() + ":8091/LdZl";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else if (!RZL && !RLL) {

			url = "http://" + atendimento.getIp() + ":8091/LdZd";
			envioComando = new ThreadEnvioComando(url);
			envioComando.start();
			System.out.println(url);

		} else
		{
			envioComando = new ThreadEnvioComando("");
		}
		
		int timeout=0;
		for(;;)
		{
			timeout++;
			Periodo.delay(25);
			if(envioComando.getState().equals(Thread.State.TERMINATED)){
				break;
				
			}
			
			if(timeout>22){
				envioComando.interrupt();
				System.out.println("Sem resposta no IP: " + atendimento.getIp() );
				clienteRepo = new ClienteRepo();
				clienteRepo.AtualizarStatusApto(atendimento);
				break;
			}
		}
		System.out.println(url);

	}

	@Override
	public void run() {

		for (;;) {

			List<Conexao> conexao = new ArrayList<Conexao>();
			conexaoRepo = new ConexaoRepo();
			List<Cliente> clientes = new ArrayList<Cliente>();
			clienteRepo = new ClienteRepo();
			clientes = clienteRepo.BuscarTodosMacHabilitados();
			conexao = conexaoRepo.buscarTodos();
			if (!conexao.isEmpty()) {
				conn = new OracleConnection(conexao.get(0),
						ConsultaExterna.parametros(clientes));
				processamento(conn.connetion(), clientes);
			}

			Periodo.delay(1000);
		}
	}
}