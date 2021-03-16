package br.com.dynamic.estrutura;

import java.util.ArrayList;
import java.util.List;
import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConsultaExternaRepo;

public class ThreadConsultaRegistrados extends Thread {

	ConsultaExterna consultaExterna = new ConsultaExterna();
	ConsultaExternaRepo consultaExternaRepo = new ConsultaExternaRepo();
	ClienteRepo clienteRepo;
	AtendimentoRepo atendimentoRepo;
	private Atendimento atendimento;
	List<ConsultaExterna> aptosSistemaIntegrado;

	public ThreadConsultaRegistrados(Atendimento atendimento, List<ConsultaExterna> aptosSistemaIntegrado) {
		this.atendimento = atendimento;
		this.aptosSistemaIntegrado = aptosSistemaIntegrado;
	}

	public void cadastrarQuartos(List<ConsultaExterna> funcionarios) {

		Cliente novoApto = new Cliente();
		clienteRepo = new ClienteRepo();

		for (ConsultaExterna aptoExterno : funcionarios) {
			List<Cliente> apto = clienteRepo.BuscarNovoApto(aptoExterno
					.getnDoQuarto());
			if (apto.isEmpty()) {
				novoApto.setFone(null);
				novoApto.setNome(aptoExterno.getnDoQuarto());
				novoApto.setCpf(aptoExterno.getStatusDoQuarto());
				clienteRepo = new ClienteRepo(novoApto);
				clienteRepo.salvar();
				novoApto = new Cliente();
			}

		}

	}

	public void processamento(List<ConsultaExterna> funcionarios,
			Atendimento mac) {

		ControleManual controleManual = new ControleManual();
		List<Cliente> aptoExiste = new ArrayList<Cliente>();
		Cliente aptoEnvio = new Cliente();
		clienteRepo = new ClienteRepo();
		aptoExiste = clienteRepo.BuscarNovoAptoPorMac(
				mac.getId().getIdAtendimento());
		if(!aptoExiste.isEmpty()){
		aptoEnvio = aptoExiste.get(0);
		for (ConsultaExterna aptoExterno : funcionarios) {
			if (aptoEnvio.getNome()
					.equalsIgnoreCase(aptoExterno.getnDoQuarto())) {
				System.out.println("enviando mensagem de comando");
				aptoEnvio.setCpf(aptoExterno.getStatusDoQuarto());
				if (aptoEnvio.getFone2().equalsIgnoreCase(
				"LayoutNovo/Imagem/AUTO.png")) {
				enviarNovoStatus(aptoEnvio, mac);
				}
				clienteRepo = new ClienteRepo(aptoEnvio);
				//clienteRepo.salvar();
			}
		}
	}

	}

	public void enviarNovoStatus(Cliente apto, Atendimento mac) {

		HttpURL clientHttp = new HttpURL();
		String url = "";

		if ("L".equalsIgnoreCase(apto.getCpf())) { // locado

			url = "http://" + mac.getIp() + ":8091/LOCA";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("E".equalsIgnoreCase(apto.getCpf())) { // encerramento

			url = "http://" + mac.getIp() + ":8091/ENCE";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("D".equalsIgnoreCase(apto.getCpf())) {// disponivel

			url = "http://" + mac.getIp() + ":8091/DISP";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("M".equalsIgnoreCase(apto.getCpf())) { // manutencao

			url = "http://" + mac.getIp() + ":8091/MANU";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("R".equalsIgnoreCase(apto.getCpf())) { // reservado

			url = "http://" + mac.getIp() + ":8091/RESE";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("X".equalsIgnoreCase(apto.getCpf())) { // faxina

			url = "http://" + mac.getIp() + ":8091/FAXI";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("A".equalsIgnoreCase(apto.getCpf())) {// Aguardando

			url = "http://" + mac.getIp() + ":8091/SUJO";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("Z".equalsIgnoreCase(apto.getCpf())) { // limpeza

			url = "http://" + mac.getIp() + ":8091/LIMP";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("P".equalsIgnoreCase(apto.getCpf())) { // pernoite

			url = "http://" + mac.getIp() + ":8091/PERN";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("V".equalsIgnoreCase(apto.getCpf())) { // revisao

			url = "http://" + mac.getIp() + ":8091/REVI";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("I".equalsIgnoreCase(apto.getCpf())) { // diaria

			url = "http://" + mac.getIp() + ":8091/DIAR";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("C".equalsIgnoreCase(apto.getCpf())) { // reconferencia

			url = "http://" + mac.getIp() + ":8091/RECO";
			clientHttp.acessarURL(url);
			System.out.println(url);

		} else if ("F".equalsIgnoreCase(apto.getCpf())) {

			url = "http://" + mac.getIp() + ":8091/LOCF";
			clientHttp.acessarURL(url);
			System.out.println(url);

		}
	}

	@Override
	public void run() {

		//cadastrarQuartos(aptosSistemaIntegrado);

		HttpURL clientHttp = new HttpURL();
		clientHttp.verificarIpEMac("http://" + atendimento.getIp()
				+ ":8091/INFO");

		Periodo.delay(100);
		
		processamento(aptosSistemaIntegrado, atendimento);

	}
}