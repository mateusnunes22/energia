package br.com.dynamic.estrutura;

import java.util.ArrayList;
import java.util.List;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Conexao;
import br.com.dynamic.estrutura.ThreadConsulta;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConexaoRepo;

/**
 * 
 * @author
 */
public class ThreadBroadcastForcadaUnica extends Thread {

	ClienteRepo clienteRepo;

	@Override
	public void run() {

		ThreadConsulta enviarMensagem;
		boolean isTodosOperando = false;
		int totalOperando = 0, operandoAgora = 0;
		List<Cliente> clientes = new ArrayList<Cliente>();

		clienteRepo = new ClienteRepo();

		clientes = clienteRepo.BuscarTodosMacHabilitados();
		totalOperando = clientes.size();

		for (Cliente cliente : clientes) {
			if (cliente.getStatus().equalsIgnoreCase("Operando")) {
				operandoAgora++;
			}
		}
		if (operandoAgora == totalOperando) {
			isTodosOperando = true;
		}


			for (int i = 0; i < 255; i++) {

				enviarMensagem = new ThreadConsulta(i);

				enviarMensagem.start();

				Periodo.delay(35);


		}

	}
}