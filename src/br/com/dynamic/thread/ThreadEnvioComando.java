package br.com.dynamic.thread;

import br.com.dynamic.estrutura.HttpURL;

public class ThreadEnvioComando extends Thread {

	private String args;

	public ThreadEnvioComando(String args) {
		this.args = args;
	}
	
	@Override
	public void run() {

		HttpURL clientHttp = new HttpURL();
		clientHttp.verificarIpEMac(args);

	}
}