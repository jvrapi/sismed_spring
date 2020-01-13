package br.com.sismed.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.JDBCException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.MetaDataAccessException;


@EnableJpaRepositories
@Configuration
public class DataBaseConfig {

	@Bean
    public DataSource data() {
		 DriverManagerDataSource bd = new DriverManagerDataSource();
		 bd.setDriverClassName("com.mysql.cj.jdbc.Driver");
		try {
       
       System.out.println("Entrei no try");
        bd.setUrl("jdbc:mysql://localhost:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
        bd.setUsername("root");
        bd.setPassword("172010");
		}catch(CannotGetJdbcConnectionException g) {
			 System.out.println("Entrei no catch");
	        bd.setUrl("jdbc:mysql://localhost:3306/tresta?useTimezone=true&serverTimezone=America/Sao_Paulo");
	        bd.setUsername("root");
	        bd.setPassword("172010joao");
		}
        
        return bd;
        
        
    }
}
