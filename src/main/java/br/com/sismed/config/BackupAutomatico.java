package br.com.sismed.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BackupAutomatico {

	private static final String TIME_ZONE = "America/Sao_Paulo";

	@Autowired
	private DataSource datasource;

	@Value("${spring.datasource.username}")
	private String host;

	@Value("${spring.datasource.password}")
	private String password;

	private String dataBase = "tresta";

	@Scheduled(cron = "0 11 11 * * *", zone = TIME_ZONE) // anotação que faz o agendamento da execução. O cron serve
															// para dizer quando sera executado
	public void gerarBackup() throws SQLException {
		Connection metaData = datasource.getConnection();
		Statement s = metaData.createStatement();
		ResultSet r = s.executeQuery("SHOW TABLES;");
		List<String> tabelas = new ArrayList<String>();
		while (r.next()) {
			tabelas.add(r.getString("Tables_in_tresta"));
		}
		
		
		LocalDate data = LocalDate.now();
		
		for(String t : tabelas) {
			String arquivo = t + ".sql";

			// caminho aonde sera salvo o arquivo mysql. Nesse momento, a pasta com a data
			// atualnão está criada
			String caminho = "C:\\sismed\\backup\\" + data + "\\automatico\\";

			// String do mysql para gerar o arquivo sql
			String dump = "mysqldump -u " + host + " -p" + password + " " + dataBase + " " + " "+ t + " > " + caminho + arquivo;

			/*
			 * Comandos que serão executados a primeira string, define aonde sera salvo o
			 * arquivo de backup. logo depois, cria uma pasta com a data atual, atraves do
			 * comando 'md' e por ultimo, ultiliza o comando dump do mysql da maquina para
			 * gerar o arquivo
			 * 
			 */
			String[] comando = { "cd C:\\sismed\\backup", "md " + data, "cd " + data,"md automatico", dump };
			try {
				ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join("& ", comando));
				builder.redirectErrorStream(true);
				builder.start();

			} catch (Exception a) {
				a.printStackTrace();
			}
		}

		
	}

}
