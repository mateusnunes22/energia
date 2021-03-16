package br.com.dynamic.estrutura;

import br.com.dynamic.entidade.Cliente;
import br.com.dynamic.repo.ClienteRepo;


public class ControleManual{
	
	ClienteRepo clienteRepo;
	Cliente cliente = new Cliente();

	public void atualizarStatus(Cliente apto) {
		

		if ("L".equalsIgnoreCase(apto.getCpf())) { // locado

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/ligado.png");
			
		} else if ("E".equalsIgnoreCase(apto.getCpf())) { // encerramento

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/ligado.png");
			
		} else if ("D".equalsIgnoreCase(apto.getCpf())) {// disponivel

			apto.setRetorno("LayoutNovo/Imagem/desligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("M".equalsIgnoreCase(apto.getCpf())) { // manutencao

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("R".equalsIgnoreCase(apto.getCpf())) { // reservado

			apto.setRetorno("LayoutNovo/Imagem/desligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("X".equalsIgnoreCase(apto.getCpf())) { // faxina

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("A".equalsIgnoreCase(apto.getCpf())) {// Aguardando

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("Z".equalsIgnoreCase(apto.getCpf())) { // limpeza

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("P".equalsIgnoreCase(apto.getCpf())) { // pernoite

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/ligado.png");

		} else if ("V".equalsIgnoreCase(apto.getCpf())) { // revisao

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("I".equalsIgnoreCase(apto.getCpf())) { // diaria

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/ligado.png");

		} else if ("C".equalsIgnoreCase(apto.getCpf())) { // reconferencia

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/desligado.png");

		} else if ("F".equalsIgnoreCase(apto.getCpf())) {

			apto.setRetorno("LayoutNovo/Imagem/ligado.png");
			apto.setRenovacao("LayoutNovo/Imagem/ligado.png");

		}
		
		clienteRepo = new ClienteRepo(apto);
		clienteRepo.updateControleManual();
		
	}
	
}
