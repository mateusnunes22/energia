package br.com.dynamic.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.estrutura.ConsultaExterna;
import br.com.dynamic.estrutura.OracleConnection;
import br.com.dynamic.estrutura.ThreadConsulta;
import br.com.dynamic.estrutura.ThreadConsultaRegistrados;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConexaoRepo;

/**
 * 
 * @author dcn
 */
public class ComunicacaoBroadcast implements Job {

	ClienteRepo clienteRepo;
	AtendimentoRepo atendimentoRepo;
	ConexaoRepo conexaoRepo;

	public void execute(JobExecutionContext jec) throws JobExecutionException {

		ThreadConsulta enviarMensagem;
		ThreadConsultaRegistrados enviarMensagemIdentificados;
		boolean isTodosOperando = false;
		int totalOperando = 0, operandoAgora = 0;
		List<Conexao> conexao = new ArrayList<Conexao>();
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		clienteRepo = new ClienteRepo();
		atendimentoRepo = new AtendimentoRepo();
		conexaoRepo = new ConexaoRepo();
		conexao = conexaoRepo.buscarTodos();
		if (!conexao.isEmpty()) {
			clientes = clienteRepo.BuscarTodosMacHabilitados();
			totalOperando = clientes.size();
			OracleConnection conn = new OracleConnection(conexao.get(0),ConsultaExterna.parametros(clientes));
			List<ConsultaExterna> aptosSistemaIntegrado = new ArrayList<ConsultaExterna>();
			aptosSistemaIntegrado = conn.connetion();

			for (Cliente cliente : clientes) {
				if ("Operando".equalsIgnoreCase(cliente.getStatus())) {
					operandoAgora++;
				}
			}
			if (operandoAgora == totalOperando) {
				isTodosOperando = true;
				atendimentos = atendimentoRepo.getLista();

			}
			//if (isTodosOperando && totalOperando != 0) { Original
			if (true) {
				for (int i = 0; i < operandoAgora; i++) {

					enviarMensagemIdentificados = new ThreadConsultaRegistrados(
							atendimentos.get(i), aptosSistemaIntegrado);

					enviarMensagemIdentificados.start();

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} else {
				for (int i = 0; i < 255; i++) {

					enviarMensagem = new ThreadConsulta(i);

					enviarMensagem.start();

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}

		}
	}
}