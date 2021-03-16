package br.com.dynamic.estrutura;

import java.sql.*;
import java.util.ArrayList;

import br.com.dynamic.entidade.Conexao;

public class OracleConnection {

	private Conexao dadosConexao;
	private String parametro;

	public OracleConnection() {

	}

	public OracleConnection(Conexao dadosConexao, String parametro) {
		this.dadosConexao = dadosConexao;
		this.parametro = parametro;
	}

	public ArrayList<ConsultaExterna> connetion() {
		String sql;
		ArrayList<ConsultaExterna> aptos = new ArrayList<ConsultaExterna>();

		/*
		 * O driver JDBC do Oracle está disponível no seguinte endereço:
		 * http://www
		 * .oracle.com/technetwork/database/features/jdbc/index-091264.html
		 */

		// Configuração dos parâmetros de conexão
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String server = dadosConexao.getIp();// "10.11.20.2";
		String port = dadosConexao.getPorta();// "1521"; Porta TCP padrão do
												// Oracle
		String database = dadosConexao.getBaseDeDados();// "xe";

		// Configuração dos parâmetros de autenticação
		String user = dadosConexao.getSsid();// "bitzdba";
		String passwd = dadosConexao.getSenha();

		try {
			String url = "jdbc:oracle:thin:@" + server + ":" + port + ":"
					+ database;

			// Abre-se a conexão com o Banco de Dados
			Connection con = DriverManager.getConnection(url, user, passwd);
			// System.out.println("conectado com sucesso");
			// Cria-se Statement com base na conexão con
			Statement stmt = con.createStatement();
			// System.out.println("gerado statement");
			// Exemplo: cria-se uma tabela no Banco de Dados de Teste
			/*
			 * sql = "CREATE TABLE `filmes` (" +
			 * "`id` INT UNSIGNED NOT NULL AUTO_INCREMENT," +
			 * "`titulo` VARCHAR(80) NOT NULL," + "`ano` INT UNSIGNED," +
			 * "`diretor` VARCHAR(80)," + "PRIMARY KEY (`id`))" +
			 * "CHARACTER SET utf8"; stmt.executeUpdate(sql);
			 */

			// Exemplo: inserindo dados na tabela de filmes
			/*
			 * sql = "INSERT INTO `filmes` (`titulo`, `ano`, `diretor`)" +
			 * "VALUES ('The Matrix', 1999, 'Andy Wachowski & Larry Wachowski')"
			 * ; stmt.executeUpdate(sql);
			 * 
			 * sql = "INSERT INTO `filmes` (`titulo`, `ano`, `diretor`)" +
			 * "VALUES ('The Matrix Reloaded', 2003, 'Andy Wachowski & Larry Wachowski')"
			 * ; stmt.executeUpdate(sql);
			 * 
			 * sql = "INSERT INTO `filmes` (`titulo`, `ano`, `diretor`)" +
			 * "VALUES ('The Matrix Revolutions', 2003, 'Andy Wachowski & Larry Wachowski')"
			 * ; stmt.executeUpdate(sql);
			 */

			// Exemplo: navegando e exibindo os dados dos filmes

			// para o sistema energia tabela: QUARTOS
			// colunas: STATUS , NROQUARTO
			// sql = "SELECT STATUS,NROQUARTO FROM QUARTOS";
			if(!parametro.equalsIgnoreCase("")){
			sql = "SELECT STATUS,NROQUARTO FROM QUARTOS WHERE NROQUARTO IN ("
					+ parametro + ")";
			}
			else
			{
				sql = "SELECT STATUS,NROQUARTO FROM QUARTOS";
			}
			
			// sql = "SELECT `titulo`,`ano` FROM `filmes`";

			// Executa-se a consulta dos campos titulo,ano da tabela de filmes
			ResultSet res = stmt.executeQuery(sql);

			ConsultaExterna apto = new ConsultaExterna();

			while (res.next()) {
				apto.setStatusDoQuarto(res.getString("STATUS"));
				apto.setnDoQuarto(res.getString("NROQUARTO"));
				aptos.add(apto);
				apto = new ConsultaExterna();
				// System.out.println("ROW = " + res.getString("STATUS") + ": "
				// + res.getString("NROQUARTO"));
			}
			
			// Exemplo: excluindo a tabela filmes do Banco de Dados de Teste
			// sql = "DROP TABLE `filmes`";
			// stmt.executeUpdate(sql);

			con.close();
		} catch (Exception e) {
			System.out
					.println("Falha ao tentar acessar banco de dados da integração");
			e.printStackTrace();

		}
		return aptos;
	}
}