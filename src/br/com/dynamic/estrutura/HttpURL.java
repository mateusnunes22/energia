package br.com.dynamic.estrutura;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.AtendimentoId;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;

public class HttpURL {

	AtendimentoRepo atendimentoRepo;
	ClienteRepo clienteRepo;

	public void acessarURL(String args) {

		if (args.isEmpty()) {
			System.out.println("Não foi especificado nenhuma URL.");
		}

		// Pegando a url passada como parametro.
		String urlName = args;

		try {

			URL url = new URL(urlName);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			String line = null;
			System.out
					.println("Inicio da mensagem de resposta******************");
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

			System.out
					.println("Fim da mensagem de comando**********************");
			in.close();
			urlConnection.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("Erro ao criar URL. Formato inválido.");
		} catch (IOException e2) {
			// e2.printStackTrace();
			System.out.println("Erro de comunicação com o Mac instalado em:"
					+ args);
		}

	}

	public void verificarIpEMac(String args) {

		if (args.isEmpty()) {
			System.out.println("Não foi especificado nenhuma URL.");
		}

		// Pegando a url passada como parametro.
		String urlName = args;
		String redeIp = "";
		for (int i = 0; i < args.length(); i++) {

			if (String.valueOf(args.charAt(i)).equalsIgnoreCase("/") && i < 10) {
				redeIp = args.substring(i + 1);
			}

			if (String.valueOf(args.charAt(i)).equalsIgnoreCase(":") && i > 10) {
				redeIp = redeIp.substring(0, i - 7);
			}
		}

		boolean temApto = false;
		Cliente apto = new Cliente();
		Atendimento atendimento = new Atendimento();
		clienteRepo = new ClienteRepo();
		atendimentoRepo = new AtendimentoRepo();
		if (!atendimentoRepo.getForIp(redeIp).isEmpty()) {
			atendimento = atendimentoRepo.getForIp(redeIp).get(0);

			if (!clienteRepo.BuscarNovoAptoPorMac(
					atendimento.getId().getIdAtendimento()).isEmpty()) {
				apto = clienteRepo.BuscarNovoAptoPorMac(
						atendimento.getId().getIdAtendimento()).get(0);
				temApto = true;
			}
		}
		try {

			URL url = new URL(urlName);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			try {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream()));

				String line = null;
				String ip = "", mac = "";
				int timeout = 0;
				System.out
						.println("Inicio da mensagem de resposta******************");
				boolean isNewHardWare = false;
				while ((line = in.readLine()) != null && timeout < 40) {
					timeout++;

					if (timeout > 38) {
						System.out.println("resposta incompleta no IP: "
								+ args.substring(7, 23));
						if (temApto) {
							clienteRepo.AtualizarStatusApto(atendimento);
						}
						break;
					}

					if (isNewHardWare) {
						for (int i = 0; i < line.length(); i++) {
							if (String.valueOf(line.charAt(i))
									.equalsIgnoreCase("/")) {
								ip = line.substring(0, i);
								mac = line.substring(i + 1);

							}
						}
						System.out.println(ip);
						System.out.println(mac);
						atendimento = new Atendimento();

						atendimento.setIp(ip);
						atendimento.setMac(mac);
						atendimentoRepo = new AtendimentoRepo(atendimento);
						clienteRepo = new ClienteRepo();
						if (!atendimentoRepo.getForMac().isEmpty()) {
							atendimento = atendimentoRepo.getForMac().get(0);
							atendimento.setIp(ip);
						}
						atendimentoRepo = new AtendimentoRepo(atendimento);
						atendimentoRepo.salvar();
						clienteRepo.AtualizarStatusApto(atendimento);

						break;
					}

					System.out.println(line);
					if (line.equalsIgnoreCase("!WEC!")) {
						isNewHardWare = true;
					}

				}

				System.out
						.println("Fim da mensagem de comando**********************");
				in.close();
				urlConnection.disconnect();

			} catch (Exception e) {
				System.out.println("Erro ao receber resposta do IP: "
						+ args.substring(7, 23));
				clienteRepo = new ClienteRepo();
				if (temApto) {
					clienteRepo.AtualizarStatusApto(atendimento);
				}
			}

		} catch (MalformedURLException e) {
			System.out.println("Erro ao criar comunicação com o IP: "
					+ args.substring(7, 23));
			clienteRepo = new ClienteRepo();
			if (temApto) {
				clienteRepo.AtualizarStatusApto(atendimento);
			}
			// System.out.println("Erro ao criar URL. Formato inválido.");
		} catch (IOException e2) {
			// e2.printStackTrace();
			System.out.println("Sem resposta no IP: " + args.substring(7, 23));
			clienteRepo = new ClienteRepo();
			if (temApto) {
				clienteRepo.AtualizarStatusApto(atendimento);
			}
		}

	}

}