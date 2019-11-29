package br.com.sismed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
public class DemoSismedApplication {

	@Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
	
	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("@Rapi172010@"));
	
		
		SpringApplication.run(DemoSismedApplication.class, args);
	}

}
