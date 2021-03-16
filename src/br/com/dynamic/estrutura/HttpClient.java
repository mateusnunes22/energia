package br.com.dynamic.estrutura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
 
/**
 * Cliente HTTP simples para somente requisi��es GET
 */

public class HttpClient {
 
    /**
     * Vers�o do protocolo utilizada
     */
    public final static String HTTP_VERSION = "HTTP/1.1";
 
    private String host;
    private int port;
 
    /**
     * Construtor do cliente HTTP
     * @param host host para o cliente acessar
     * @param port porta de acesso
     */
    public HttpClient(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }
 
    /**
     * Realiza uma requisi��o HTTP e devolve uma resposta
     * @param path caminho a ser feita a requisi��o
     * @return resposta do protocolo HTTP
     * @throws UnknownHostException quando n�o encontra o host
     * @throws IOException quando h� algum erro de comunica��o
     */
    public String getURIRawContent(String path) throws UnknownHostException,
            IOException {
        Socket socket = null;
        try {
            // Abre a conex�o
            socket = new Socket(this.host, this.port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
 
            // Envia a requisi��o
            out.println("GET " + path + " " + HTTP_VERSION);
            out.println("Host: " + this.host);
            out.println("Connection: Close");
            out.println();
 
            boolean loop = true;
            StringBuffer sb = new StringBuffer();
 
            // recupera a resposta quando ela estiver dispon�vel
            while (loop) {
                if (in.ready()) {
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        sb.append((char) i);
                    }
                    loop = false;
                }
            }
            return sb.toString();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
