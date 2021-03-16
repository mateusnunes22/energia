package br.com.dynamic.estrutura;

import java.util.Date;

public class Periodo{

	private Date dataInicial;
	private Date dataFinal;
	
	
	public Periodo() {
		this.setDataInicial(new Date());
		this.setDataFinal(new Date());
	}

	public Periodo(Date dataInicial, Date dataFinal) {
		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
	}
	
	public static void delay(int miliSegundos){
		try {
			Thread.sleep(miliSegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return dataFinal;
	}
	
}
