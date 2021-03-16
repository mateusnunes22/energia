package br.com.dynamic.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.entidade.Funcionario;
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
 * @author dcn
 */
public class TestJob implements Job {

	ConsultaExterna consultaExterna = new ConsultaExterna();
	ConsultaExternaRepo consultaExternaRepo = new ConsultaExternaRepo();
	ClienteRepo clienteRepo;
	AtendimentoRepo atendimentoRepo;
	ConexaoRepo conexaoRepo;

	public void processamento(ArrayList<ConsultaExterna> funcionarios,
			List<Cliente> atualizacaoRele) {

		HttpURL clientHttp = new HttpURL();
		Cliente novoApto = new Cliente();
		clienteRepo = new ClienteRepo();
		atendimentoRepo = new AtendimentoRepo();
		Atendimento atendimento = new Atendimento();

		ControleManual controleManual = new ControleManual();

		for (Cliente atualizacao : atualizacaoRele) {
			if (atualizacao.getFone2().equalsIgnoreCase(
					"LayoutNovo/Imagem/AUTO.png")) {
				controleManual.atualizarStatus(atualizacao);
				atendimento = atendimentoRepo.buscarForgId(
						atualizacao.getIdAtendimento()).get(0);
				clientHttp.verificarIpEMac("http://" + atendimento.getIp()
						+ ":8091/INFO");
				Periodo.delay(100);
				enviarNovoStatus(atualizacao, atendimento);
			}
		}

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
					if(!atendimentoRepo.buscarForgId(
							novoApto.getIdAtendimento()).isEmpty()){
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

		HttpURL clientHttp = new HttpURL();
		String url = "";

		if ("L".equalsIgnoreCase(apto.getCpf())) { // locado

			url = "http://" + atendimento.getIp() + ":8091/LOCA";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("E".equalsIgnoreCase(apto.getCpf())) { // encerramento

			url = "http://" + atendimento.getIp() + ":8091/ENCE";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("D".equalsIgnoreCase(apto.getCpf())) {// disponivel

			url = "http://" + atendimento.getIp() + ":8091/DISP";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("M".equalsIgnoreCase(apto.getCpf())) { // manutencao

			url = "http://" + atendimento.getIp() + ":8091/MANU";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("R".equalsIgnoreCase(apto.getCpf())) { // reservado

			url = "http://" + atendimento.getIp() + ":8091/RESE";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("X".equalsIgnoreCase(apto.getCpf())) { // faxina

			url = "http://" + atendimento.getIp() + ":8091/FAXI";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("A".equalsIgnoreCase(apto.getCpf())) {// Aguardando

			url = "http://" + atendimento.getIp() + ":8091/SUJO";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("Z".equalsIgnoreCase(apto.getCpf())) { // limpeza

			url = "http://" + atendimento.getIp() + ":8091/LIMP";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("P".equalsIgnoreCase(apto.getCpf())) { // pernoite

			url = "http://" + atendimento.getIp() + ":8091/PERN";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("V".equalsIgnoreCase(apto.getCpf())) { // revisao

			url = "http://" + atendimento.getIp() + ":8091/REVI";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("I".equalsIgnoreCase(apto.getCpf())) { // diaria

			url = "http://" + atendimento.getIp() + ":8091/DIAR";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("C".equalsIgnoreCase(apto.getCpf())) { // reconferencia

			url = "http://" + atendimento.getIp() + ":8091/RECO";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("F".equalsIgnoreCase(apto.getCpf())) {

			url = "http://" + atendimento.getIp() + ":8091/LOCF";
			clientHttp.acessarURL(url);
			System.out.println(url);

		}

	}

	public void execute(JobExecutionContext jec) throws JobExecutionException {

		List<Conexao> conexao = new ArrayList<Conexao>();
		conexaoRepo = new ConexaoRepo();
		List<Cliente> clientes = new ArrayList<Cliente>();
		clienteRepo = new ClienteRepo();
		clientes = clienteRepo.BuscarTodosMacHabilitados();
		conexao = conexaoRepo.buscarTodos();
		if (!conexao.isEmpty()) {
			OracleConnection conn = new OracleConnection(conexao.get(0),
					ConsultaExterna.parametros(clientes));
			processamento(conn.connetion(), clientes);
		}

	}
}