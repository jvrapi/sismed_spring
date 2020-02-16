package br.com.sismed.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;


public class DataBaseConfig {
	Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

	
	public DataSource dataSource() {
		DataSource dataSource = null;
		try {
			// primeiro banco
			dataSource = dataSource1();
			dataSource.getConnection().isValid(500);
		} catch (Exception banco2) {
			logger.warn(">>> DataSource 1 falhou, conectando no 2. <<<", banco2.getMessage());
			try {
				// segundo banco
				dataSource = dataSource2();
				dataSource.getConnection().isValid(500);
			} catch (Exception banco3) {
				logger.warn(">>> DataSource 2 falhou, conectando no 3. <<<", banco3.getMessage());
				try {
					// terceiro banco
					dataSource = dataSource3();
					dataSource.getConnection().isValid(500);
				} catch (Exception banco4) {
					logger.warn(">>> DataSource 3 falhou, conectando no 4. <<<", banco4.getMessage());
					try {
						// quarto banco
						dataSource = dataSource4();
						dataSource.getConnection().isValid(500);
					} catch (Exception banco5) {
						logger.warn(">>> DataSource 4 falhou, conectando no 5. <<<", banco4.getMessage());
						try {
							// quinto banco
							dataSource = dataSource5();
							dataSource.getConnection().isValid(500);
						} catch (Exception conexao_falhou) {
							logger.warn(">>> Todas as conexões com o banco falharam, verifique os dados e tente novamente <<<", conexao_falhou.getMessage());		
						}
					}
				}
			}
		}
		return dataSource;
	}

	private DataSource dataSource1() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://wjoodes04v:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("tresta");
		ds.setPassword("Mass55");
		return ds;
	}

	private DataSource dataSource2() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://wmysdes01v:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("tresta");
		ds.setPassword("Mass55");
		return ds;
	}

	private DataSource dataSource3() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("root");
		ds.setPassword("172010joao");
		return ds;
	}

	private DataSource dataSource4() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("root");
		ds.setPassword("1234");
		return ds;
	}

	private DataSource dataSource5() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
		ds.setUsername("root");

		return ds;
	}
}
