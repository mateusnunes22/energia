package br.com.dynamic.estrutura;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import br.com.dynamic.entidade.Atendimento;
import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.entidade.Funcionario;
import br.com.dynamic.estrutura.OracleConnection;
import br.com.dynamic.estrutura.ThreadConsulta;
import br.com.dynamic.repo.AtendimentoRepo;
import br.com.dynamic.repo.ClienteRepo;
import br.com.dynamic.repo.ConsultaExternaRepo;

public class ThreadConsulta extends Thread {

	private int ip;

	public ThreadConsulta(int ip) {
		this.ip = ip;
	}


	@Override
	public void run() {

		String temp = "192.168.0.";

		try {
			temp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ipPrefixo = "";
		for (int i = 0; i < temp.length(); i++) {
			if (String.valueOf(temp.charAt(i)).equalsIgnoreCase(".")) {
				ipPrefixo = temp.substring(0, i + 1);

			}
		}
		HttpURL clientHttp = new HttpURL();
		clientHttp.verificarIpEMac("http://" + ipPrefixo + ip + ":8091/INFO");

		Periodo.delay(15);

	}
}