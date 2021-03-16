package br.com.dynamic.http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor HTTP simples
 */
public class HttpServer {

	private final static Logger logger = Logger.getLogger(HttpServer.class
			.toString());

	private String host;
	private int port;

	/**
	 * Construtor do servidor de HTTP
	 * 
	 * @param host
	 *            host do servidor
	 * @param port
	 *            porta do servidor
	 */
	public HttpServer(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	/**
	 * Inicia o servidor e fica escutando no endereço e porta especificada no
	 * construtor
	 */
	public void serve() {
		ServerSocket serverSocket = null;

		//logger.info("Iniciando servidor no endereco: " + this.host
			//	+ ":" + this.port);
		System.out.println("Iniciando servidor no endereco: " + this.host
				+ ":" + this.port);
		
		try {
			// Cria a conexao servidora
			serverSocket = new ServerSocket(port, 1,
					InetAddress.getByName(host));
		} catch (IOException e) {
			//logger.log(Level.SEVERE, "Erro ao iniciar servidor!", e);
			System.out.println("Erro ao iniciar servidor!");
			return;
		}
		//logger.info("Conexao com o servidor aberta no endereco: " + this.host
			//	+ ":" + this.port);
		System.out.println("Conexao com o servidor aberta no endereco: ");
		// Fica esperando pela conexão cliente
		while (true) {
			//logger.info("Aguardando conexoes...");
			System.out.println("Aguardando conexoes...");
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				// Realiza o parse da requisição recebida
				String requestString = convertStreamToString(input);
				//logger.info("Conexao recebida. Conteudo:\n" + requestString);
				//System.out.println("Conexao recebida. Conteudo:\n" + requestString);
				Request request = new Request();
				request.parse(requestString);

				// recupera a resposta de acordo com a requisicao
				
				Response response = ResponseFactory.createResponse(request);
				String responseString = response.respond();
				//logger.info("Resposta enviada. Conteudo:\n" + responseString);
				//System.out.println("Resposta enviada. Conteudo:\n" + responseString);
				output.write(responseString.getBytes());

				// Fecha a conexao
				socket.close();

			} catch (Exception e) {
				//logger.log(Level.SEVERE, "Erro ao executar servidor!", e);
				System.out.println("Erro ao executar servidor!");
				continue;
			}
		}
	}

	private String convertStreamToString(InputStream is) {

		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[2048];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is));
				int i = reader.read(buffer);
				writer.write(buffer, 0, i);
			} catch (IOException e) {
				//logger.log(Level.SEVERE, "Erro ao converter stream para string", e);
				System.out.println("Erro ao converter stream para string");
				return "";
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	/*public static void main(String[] args) {
		HttpServer server = new HttpServer("localhost", 8091);
		server.serve();
	}*/

}
