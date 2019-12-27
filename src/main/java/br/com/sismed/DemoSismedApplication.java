package br.com.sismed;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

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
	
	
	 @Autowired
		JavaMailSender mail;
	 
	 @Value("${spring.mail.username}")
	 String from;
		
		@Override
		public void run(String... args) throws Exception {
			SimpleMailMessage simple = new SimpleMailMessage();
			simple.setFrom(from);
			simple.setTo("joaooviitorr@hotmail.com");
			simple.setText("testando o @value");
			simple.setSubject("testando envio de email");
			mail.send(simple);
		}
}
