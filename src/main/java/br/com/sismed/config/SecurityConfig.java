package br.com.sismed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LoginService;

@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		// acessos públicos liberados
		.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**","/primeiro/acesso").permitAll()
		
		
		
		//acessos privados de medico
		.antMatchers("/RegistroClinico/**").hasAuthority("MEDICO")
		
		
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login-error")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/acesso-negado");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder()); // tipo de criptografia
	}

	
	
}
