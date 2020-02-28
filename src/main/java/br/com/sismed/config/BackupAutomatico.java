package br.com.sismed.config;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BackupAutomatico {
	
	 private static final String TIME_ZONE = "America/Sao_Paulo";
	 
		@Value("${spring.datasource.username}")
		private String host;

		@Value("${spring.datasource.password}")
		private String password;

		private String dataBase = "tresta";
	
	@Scheduled(cron = "0 36 14 * * *", zone = TIME_ZONE) // anotação que faz o agendamento da execução. O cron serve para dizer quando sera executado
	public void gerarBackup() {
		
		LocalDate data = LocalDate.now();
		String arquivo = "BackupAutomatico.sql";
		
		//caminho aonde sera salvo o arquivo mysql. Nesse momento, a pasta com a data atualnão está criada
		String caminho = "D:\\backup\\" + data + "\\";
		
		// String do mysql para gerar o arquivo sql
		String dump = "mysqldump -u " + host + " -p" + password + " " + dataBase + " " + " > " + caminho + arquivo;
		
		/*
		 * Comandos que serão executados
		 * a primeira string, define aonde sera salvo o arquivo de backup.
		 * logo depois, cria uma pasta com a data atual, atraves do comando 'md'
		 * e por ultimo, ultiliza o comando dump do mysql da maquina para gerar o arquivo
		 * 
		 * */
		String[] comando = { "cd D:\\backup", "md " + data ,"cd d:\\xampp\\mysql\\bin", dump };
		
		try {
			ProcessBuilder builder = new ProcessBuilder("cmd", "/c", String.join("& ", comando));
			builder.redirectErrorStream(true);
			builder.start();
			
		} catch (Exception a) {
			a.printStackTrace();
		}
	}

}
