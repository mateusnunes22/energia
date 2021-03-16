package br.com.dynamic.estrutura;



public class Bean{

	private Periodo data;
	private String texto;
	private String pagina;
	private int numero;
	private int size;
	private float valor;
	
	
	public Bean() {
		this.data = new Periodo();
		this.texto = "";
		this.pagina = "";
		this.numero = 0;
		this.size = 0;
		this.valor = 0;
	}
	
	public Bean(Periodo data,String pagina) {
		this.data = data;
		this.pagina = pagina;
	}
	
	public Bean(Periodo data, String texto, String pagina, int numero, int size, float valor) {
		this.data = data;
		this.texto = texto;
		this.pagina = pagina;
		this.numero = numero;
		this.size = size;
		this.valor = valor;
	}

	public Periodo getData() {
		return data;
	}

	public void setData(Periodo data) {
		this.data = data;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getPagina() {
		return pagina;
	}
	
}
