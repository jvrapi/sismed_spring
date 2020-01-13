package br.com.sismed.config;



import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.zaxxer.hikari.HikariDataSource;


@EnableJpaRepositories
@Configuration
public class DataBaseConfig {
	 Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);
	 
	    @Bean
	    public DataSource dataSource() {
	        DataSource dataSource;
	        try {
	            dataSource = dataSource1();
	            dataSource.getConnection().isValid(500);
	        } catch (Exception e) {
	            logger.warn(">>> DataSource 1 falhou, conectado no 2. <<<", e.getMessage());
	            dataSource = dataSource3();
	            
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
