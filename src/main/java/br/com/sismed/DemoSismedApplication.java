package br.com.sismed;




import javax.sql.DataSource;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@EnableJpaRepositories
@SpringBootApplication
public class DemoSismedApplication  extends SpringBootServletInitializer implements CommandLineRunner{

	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	
	public static void main(String[] args) {
		
		
		SpringApplication.run(DemoSismedApplication.class, args);
	}

	 @Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return builder.sources(DemoSismedApplication.class);
		}
	
	
		
		@Override
		public void run(String... args) throws Exception {
		
		}
		
		
}
